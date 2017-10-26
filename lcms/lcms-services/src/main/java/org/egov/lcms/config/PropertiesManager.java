package org.egov.lcms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

/**
 * 
 * @author Prasad
 *
 */
@Configuration
@Getter
public class PropertiesManager {
	
	@Value("${egov.services.tenant.hostname}")
	private String tenantHostName;
	
	@Value("${egov.services.tenant.basepath}")
	private String tenantBasePath;

	@Value("${egov.services.tenant.searchpath}")
	private String tenantSearchPath;
	
	@Value("${egov.services.egov_idgen.hostname}")
	private String idHostName;
	
	@Value("${egov.services.egov_idgen.createpath}")
	private String idCreatepath;
	
	@Value("${ulb.name}")
	private String ulbName;
	
	@Value("${ulb.format}")
	private String ulbFormat;

}
