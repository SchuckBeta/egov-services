package org.egov.egf.budget.domain.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.egov.common.domain.model.Pagination;
import org.egov.common.persistence.repository.ESRepository;
import org.egov.egf.budget.domain.model.BudgetDetail;
import org.egov.egf.budget.persistence.entity.BudgetDetailEntity;
import org.egov.egf.budget.web.contract.BudgetDetailSearchContract;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetDetailESRepository extends ESRepository {

	private TransportClient esClient;
	private ElasticSearchQueryFactory elasticSearchQueryFactory;

	public BudgetDetailESRepository(TransportClient esClient, ElasticSearchQueryFactory elasticSearchQueryFactory) {
		this.esClient = esClient;
		this.elasticSearchQueryFactory = elasticSearchQueryFactory;
	}

	public Pagination<BudgetDetail> search(BudgetDetailSearchContract budgetDetailSearchContract) {
		final SearchRequestBuilder searchRequestBuilder = getSearchRequest(budgetDetailSearchContract);
		final SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		return mapToBudgetDetailList(searchResponse);
	}

	@SuppressWarnings("deprecation")
	private Pagination<BudgetDetail> mapToBudgetDetailList(SearchResponse searchResponse) {
		Pagination<BudgetDetail> page = new Pagination<>();
		if (searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() == 0L) {
			return page;
		}
		List<BudgetDetail> budgetDetails = new ArrayList<BudgetDetail>();
		BudgetDetail budgetDetail = null;
		for (SearchHit hit : searchResponse.getHits()) {

			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			try {
				budgetDetail = mapper.readValue(hit.sourceAsString(), BudgetDetail.class);
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

			budgetDetails.add(budgetDetail);
		}

		page.setTotalResults(Long.valueOf(searchResponse.getHits().getTotalHits()).intValue());
		page.setPagedData(budgetDetails);

		return page;
	}

	private SearchRequestBuilder getSearchRequest(BudgetDetailSearchContract criteria) {
		List<String> orderByList = new ArrayList<>();
		if (criteria.getSortBy() != null && !criteria.getSortBy().isEmpty()) {
			validateSortByOrder(criteria.getSortBy());
			validateEntityFieldName(criteria.getSortBy(), BudgetDetailEntity.class);
			orderByList = elasticSearchQueryFactory.prepareOrderBys(criteria.getSortBy());
		}

		final BoolQueryBuilder boolQueryBuilder = elasticSearchQueryFactory.searchBudgetDetail(criteria);
		SearchRequestBuilder searchRequestBuilder = esClient.prepareSearch(BudgetDetail.class.getSimpleName().toLowerCase())
				.setTypes(BudgetDetail.class.getSimpleName().toLowerCase());
		if (!orderByList.isEmpty()) {
			for (String orderBy : orderByList) {
				searchRequestBuilder = searchRequestBuilder.addSort(orderBy.split(" ")[0],
						orderBy.split(" ")[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
			}
		}

		searchRequestBuilder.setQuery(boolQueryBuilder);
		return searchRequestBuilder;
	}

}