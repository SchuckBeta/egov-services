package org.egov.eis.repository.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.eis.config.ApplicationProperties;
import org.egov.eis.utils.FileUtils;
import org.egov.eis.web.contract.PositionGetRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class PositionQueryTest {

	private static String[] expectedQueries;
	// Using static block to execute & initialise expectedQueries before testConditions() method executes
	// Can't initialise it in @BeforeClass method as @BeforeClass method executes after @Parameters method
	static {
		expectedQueries = getFileContents();
	}

	private String expectedQuery;
	private PositionGetRequest criteria;
	private List<String> expectedParams;
	
	public PositionQueryTest(PositionGetRequest criteria, String expectedQuery, List<String> expectedParams) {
		this.criteria = criteria;
		this.expectedQuery = expectedQuery;
		this.expectedParams = expectedParams;
	}

	@Mock
	private ApplicationProperties applicationProperties;

	@InjectMocks
	private PositionQueryBuilder positionQueryBuilder;

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}


	/**
	 * This method have two dimensional array of three objects:
	 * 1. NomineeGetRequest	: to pass as input search criteria
	 * 2. Expected Query	: to compare it with the returned query from getQuery method
	 * 3. Expected Params	: to compare it with the returned parameters list
	 *
	 * This approach of testing is known as Parameterized Testing. In this type of testing, jUnit runs the single
	 * test as many times as the specified conditions. For this, jUnit initialises every instance variable with
	 * the respective objects defined in the array using parameterised constructor every time the test runs.
	 * Then we can use these instance variables in our test function to either compare with output or pass as input.
	 *
	 * Since this test runs as many times as the array size without even touching the actual test method,
	 * if you want to add more conditions or simply update a particular condition, you just have to update this array.
	 *
	 * @return Collection
	 */
	
	@Parameters
	public static Collection testConditions() {
		Object[][] conditions = {{
				// Test 0: Passing only tenantId in Search Criteria
			PositionGetRequest.builder().tenantId("default").build(),
				expectedQueries[0],
				new ArrayList<>(Arrays.asList("default", "10", "0"))
		}, {
				// Test 2: Passing tenantId & list of ids in Search Criteria
			PositionGetRequest.builder().tenantId("default").id(Arrays.asList(1L, 6L)).build(),
				expectedQueries[1],
				new ArrayList<>(Arrays.asList("default", "10", "0"))
		}, {
				// Test 4: Passing tenantId & name in Search Criteria
			PositionGetRequest.builder().tenantId("default").name("clerk").build(),
				expectedQueries[2],
				new ArrayList<>(Arrays.asList("default", "clerk", "10", "0"))
		}, {
				// Test 5: Passing tenantId & toPosition in Search Criteria
			PositionGetRequest.builder().tenantId("default").departmentId(10L).build(),
				expectedQueries[3],
				new ArrayList<>(Arrays.asList("default", "10", "10", "0"))
	    }, {
			// Test 6: Passing tenantId & sortBy in Search Criteria
	    	PositionGetRequest.builder().tenantId("default").sortBy("p.id").build(),
			expectedQueries[4],
			new ArrayList<>(Arrays.asList("default", "10", "0"))
	    }, {
			// Test 7: Passing tenantId & sortOrder in Search Criteria
	    	PositionGetRequest.builder().tenantId("default").sortOrder("DESC").build(),
			expectedQueries[5],
			new ArrayList<>(Arrays.asList("default", "10", "0"))
	    }, {
			// Test 11: Passing tenantId, list of ids, name, effective date in Search Criteria
	    	PositionGetRequest.builder().tenantId("default").id(Arrays.asList(1L, 6L)).name("clerk").departmentId(10L).designationId(15L).active(true).build(),
			expectedQueries[6],
			new ArrayList<>(Arrays.asList("default", "clerk","10", "15", "true", "10", "0"))
		}};

		return Arrays.asList(conditions);
	}

	/**
	 * This test method will execute as many times as the parameters defined in the above testConditions() method.
	 * This will execute index based starting from 0 till the length - 1. So, it'll show indexes in test panel.
	 */
	@Test
	public void testGetQuery() {
		doReturn("10").when(applicationProperties).hrSearchPageSizeDefault();
		Map<String, Object> parameters = new HashMap<>();
		//List<String> parameters = new ArrayList<String>();
		//assertEquals(expectedQuery, positionQueryBuilder.getQuery(criteria, parameters));
		//assertEquals(expectedParams.toString(), parameters.toString());
	}

	/**
	 * This method reads the queries stored in a file & split & returns them as array of String
	 * @return String[]
	 */
	private static String[] getFileContents() {
		String[] expectedQueries = null;
		try {
			expectedQueries = new FileUtils()
					.getFileContents("org/egov/eis/repository/builder/PositionQueriesContainer.txt")
                    .split("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return expectedQueries;
	}
}
