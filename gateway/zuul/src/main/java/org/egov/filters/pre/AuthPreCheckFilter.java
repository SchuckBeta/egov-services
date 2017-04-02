package org.egov.filters.pre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.egov.wrapper.CustomRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import static org.egov.constants.RequestContextConstants.*;

public class AuthPreCheckFilter extends ZuulFilter {
    private static final String AUTH_TOKEN_RETRIEVE_FAILURE_MESSAGE = "Retrieving of auth token failed";
    private static final String OPEN_ENDPOINT_MESSAGE = "Routing to an open endpoint: {}";
    private static final String AUTH_TOKEN_HEADER_MESSAGE = "Fetching auth-token from header for URI: {}";
    private static final String AUTH_TOKEN_BODY_MESSAGE = "Fetching auth-token from request body for URI: {}";
    private static final String AUTH_TOKEN_HEADER_NAME = "auth-token";
    private static final String RETRIEVED_AUTH_TOKEN_MESSAGE = "Auth-token: {}";
    private static final String ROUTING_TO_ANONYMOUS_ENDPOINT_MESSAGE = "Routing to anonymous endpoint: {}";
    private static final String ROUTING_TO_PROTECTED_ENDPOINT_RESTRICTED_MESSAGE =
        "Routing to protected endpoint {} restricted - No auth token";
    private static final String UNAUTHORIZED_USER_MESSAGE = "You are not authorized to access this resource";
    private static final String PROCEED_ROUTING_MESSAGE = "Routing to an endpoint: {} - auth provided";
    private static final String NO_REQUEST_INFO_FIELD_MESSAGE = "No request-info field in request body for: {}";
    private static final String AUTH_TOKEN_REQUEST_BODY_FIELD_NAME = "authToken";
    private HashSet<String> openEndpointsWhitelist;
    private HashSet<String> anonymousEndpointsWhitelist;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;


    public AuthPreCheckFilter(HashSet<String> openEndpointsWhitelist,
                              HashSet<String> anonymousEndpointsWhitelist) {
        this.openEndpointsWhitelist = openEndpointsWhitelist;
        this.anonymousEndpointsWhitelist = anonymousEndpointsWhitelist;
        objectMapper = new ObjectMapper();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        String authToken;
        if (openEndpointsWhitelist.contains(getRequestURI())) {
            setShouldDoAuth(false);
            logger.info(OPEN_ENDPOINT_MESSAGE, getRequestURI());
            return null;
        }
        try {
            authToken = getAuthTokenFromRequest();
        } catch (IOException e) {
            logger.error(AUTH_TOKEN_RETRIEVE_FAILURE_MESSAGE, e);
            abortWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return null;
        }
        RequestContext.getCurrentContext().set(AUTH_TOKEN_KEY, authToken);
        logger.info(RETRIEVED_AUTH_TOKEN_MESSAGE, authToken);
        if (authToken == null) {
            if (anonymousEndpointsWhitelist.contains(getRequestURI())) {
                logger.info(ROUTING_TO_ANONYMOUS_ENDPOINT_MESSAGE, getRequestURI());
                setShouldDoAuth(false);
            } else {
                logger.info(ROUTING_TO_PROTECTED_ENDPOINT_RESTRICTED_MESSAGE, getRequestURI());
                abortWithStatus(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_MESSAGE);
                return null;
            }
        } else {
            logger.info(PROCEED_ROUTING_MESSAGE, getRequestURI());
            setShouldDoAuth(true);
        }
        return null;
    }

    private String getAuthTokenFromRequest() throws IOException {
        if (GET.equalsIgnoreCase(getRequestMethod()) || getRequestURI().matches(FILESTORE_REGEX)) {
            logger.info(AUTH_TOKEN_HEADER_MESSAGE, getRequestURI());
            return getAuthTokenFromRequestHeader();
        } else {
            logger.info(AUTH_TOKEN_BODY_MESSAGE, getRequestURI());
            return getAuthTokenFromRequestBody();
        }
    }

    private String getAuthTokenFromRequestBody() throws IOException {
        CustomRequestWrapper requestWrapper = new CustomRequestWrapper(getRequest());
        HashMap<String, Object> requestBody = getRequestBody(requestWrapper);
        @SuppressWarnings("unchecked")
        HashMap<String, String> requestInfo = (HashMap<String, String>) requestBody.get(REQUEST_INFO_FIELD_NAME);
        if (requestInfo == null) {
            logger.info(NO_REQUEST_INFO_FIELD_MESSAGE, getRequestURI());
            return null;
        }
        sanitizeAndSetRequest(requestBody, requestWrapper);
        return requestInfo.get(AUTH_TOKEN_REQUEST_BODY_FIELD_NAME);
    }

    private HashMap<String, Object> getRequestBody(CustomRequestWrapper requestWrapper) throws IOException {
        return objectMapper.readValue(requestWrapper.getPayload(),
            new TypeReference<HashMap<String, Object>>() { });
    }

    private void sanitizeAndSetRequest(HashMap<String, Object> requestBody, CustomRequestWrapper requestWrapper) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> requestInfo = (HashMap<String, Object>) requestBody.get(REQUEST_INFO_FIELD_NAME);

        requestInfo.remove(USER_INFO_FIELD_NAME);
        requestBody.put(REQUEST_INFO_FIELD_NAME, requestInfo);
        try {
            requestWrapper.setPayload(objectMapper.writeValueAsString(requestBody));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        RequestContext.getCurrentContext().setRequest(requestWrapper);
    }

    private String getAuthTokenFromRequestHeader() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN_HEADER_NAME);
    }

    private void setShouldDoAuth(boolean enableAuth) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set(AUTH_BOOLEAN_FLAG_NAME, enableAuth);
    }

    private String getRequestURI() {
        return getRequest().getRequestURI();
    }

    private HttpServletRequest getRequest() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest();
    }

    private String getRequestMethod() {
        return getRequest().getMethod();
    }

    private void abortWithStatus(HttpStatus status, String message) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set(ERROR_CODE_KEY, status.value());
        ctx.set(ERROR_MESSAGE_KEY, message);
        ctx.setSendZuulResponse(false);
    }
}