package org.egov.egf.master.domain.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.egov.common.domain.model.Pagination;
import org.egov.egf.master.domain.model.ChartOfAccount;
import org.egov.egf.master.web.contract.ChartOfAccountSearchContract;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChartOfAccountESRepository {

	private static final String DEFAULT_SORT_FIELD = "name";
	private TransportClient esClient;
	private ElasticSearchQueryFactory elasticSearchQueryFactory;

	public ChartOfAccountESRepository(TransportClient esClient, ElasticSearchQueryFactory elasticSearchQueryFactory) {
		this.esClient = esClient;
		this.elasticSearchQueryFactory = elasticSearchQueryFactory;
	}

	public Pagination<ChartOfAccount> search(ChartOfAccountSearchContract chartOfAccountSearchContract) {
		final SearchRequestBuilder searchRequestBuilder = getSearchRequest(chartOfAccountSearchContract);
		final SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		return mapToChartOfAccountList(searchResponse,chartOfAccountSearchContract);
	}


    @SuppressWarnings("deprecation")
	private Pagination<ChartOfAccount> mapToChartOfAccountList(SearchResponse searchResponse,ChartOfAccountSearchContract chartOfAccountSearchContract) {
		Pagination<ChartOfAccount> page = new Pagination<>();
		if (searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() == 0L) {
			return page;
		}
		List<ChartOfAccount> chartOfAccounts = new ArrayList<ChartOfAccount>();
		ChartOfAccount chartOfAccount=null;
		for (SearchHit hit : searchResponse.getHits()) {
			
			ObjectMapper mapper = new ObjectMapper();
			//JSON from file to Object
			try {
			    chartOfAccount = mapper.readValue(hit.sourceAsString(), ChartOfAccount.class);
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			chartOfAccounts.add(chartOfAccount);
		}
		
		page.setTotalResults(Long.valueOf(searchResponse.getHits().getTotalHits()).intValue());
		page.setPagedData(chartOfAccounts);

		return page;
	}

	private SearchRequestBuilder getSearchRequest(ChartOfAccountSearchContract criteria) {
		final BoolQueryBuilder boolQueryBuilder = elasticSearchQueryFactory.searchChartOfAccount(criteria);
		final SearchRequestBuilder searchRequestBuilder = esClient.prepareSearch(ChartOfAccount.class.getSimpleName().toLowerCase()).setTypes(ChartOfAccount.class.getSimpleName().toLowerCase())
				.addSort(DEFAULT_SORT_FIELD, SortOrder.ASC).setQuery(boolQueryBuilder);
		return searchRequestBuilder;
	}

}
