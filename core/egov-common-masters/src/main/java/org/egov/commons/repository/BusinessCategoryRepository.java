package org.egov.commons.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.egov.commons.model.AuthenticatedUser;
import org.egov.commons.model.BusinessCategory;
import org.egov.commons.model.BusinessCategoryCriteria;
import org.egov.commons.repository.builder.BusinessCategoryQueryBuilder;
import org.egov.commons.repository.rowmapper.BusinessCategoryRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BusinessCategoryRepository {
    public static final Logger LOGGER = LoggerFactory.getLogger(BusinessCategoryRepository.class);

	public static final String INSERT_SERVICECATEGORY_QUERY="INSERT INTO eg_businesscategory"
			+"(id,name,code,active,tenantId,createdBy,createdDate,lastModifiedBy,lastModifiedDate)"
			+" VALUES(?,?,?,?,?,?,?,?,?)";
	

	public static final String UPDATE_SERVICECATEGORY="Update eg_businesscategory"
			+" set name=?,code=?,active=?,tenantId=?,lastModifiedBy=?,lastModifiedDate=?"
			+ " where id=?";
	
	public static final String GET_CATEGORY_BY_NAME_AND_TENANTID="Select * from eg_businesscategory"
			+" where name=? and tenantId=?";
	
	public static final String GET_CATEGORY_BY_CODE_AND_TENANTID="Select * from eg_businesscategory"
			+" where code=? and tenantId=?";
	
	public static final String GET_CATEGORY_BY_ID_AND_TENANTID="Select * from eg_businesscategory"
			+" where id=? and tenantId=?";

	private static final String GET_CATEGORY_BY_CODE_TENANTID_AND_ID ="Select * from eg_businesscategory"
			+" where code=? and tenantId=? and id != ?";

	private static final String GET_CATEGORY_BY_NAME_TENANTID_AND_ID = "Select * from eg_businesscategory"
			+" where name=? and tenantId=? and id != ?";
    @Autowired 
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private BusinessCategoryRowMapper businessCategoryRowMapper;
    
    @Autowired
    private BusinessCategoryQueryBuilder businessCategoryQueryBuilder;


	public void create(BusinessCategory category) {
		Object[] obj=new Object[]{generateSequence("seq_eg_businesscategory"),category.getName(),category.getCode()
				,category.getIsactive(),category.getTenantId(),category.getCreatedBy()
				,new Date(new java.util.Date().getTime()),
				category.getLastModifiedBy(),new Date(new java.util.Date().getTime())};
	   jdbcTemplate.update(INSERT_SERVICECATEGORY_QUERY,obj);
	
	}
	
	
	public Long generateSequence(String sequenceName) {
		return jdbcTemplate.queryForObject("SELECT nextval('" + sequenceName + "')", Long.class);
	}

	public void update(BusinessCategory category) {
        Object[] obj=new Object[]{category.getName(),category.getCode()
						,category.getIsactive(),category.getTenantId(),category.getLastModifiedBy()
						,new Date(new java.util.Date().getTime()),category.getId()};
		 jdbcTemplate.update(UPDATE_SERVICECATEGORY,obj);

	}

    public List<BusinessCategory> getForCriteria(BusinessCategoryCriteria criteria) {
		List<Object> preparedStatementValues = new ArrayList<>();
		String queryStr = businessCategoryQueryBuilder.getQuery(criteria, preparedStatementValues);
		return jdbcTemplate.query(queryStr,
				preparedStatementValues.toArray(),businessCategoryRowMapper);
	}

    public boolean checkCategoryByNameAndTenantIdExists(String name, String tenantId,Long id,Boolean isUpdate) {
    	 final List<Object> preparedStatementValue = new ArrayList<Object>();
         preparedStatementValue.add(name);
         preparedStatementValue.add(tenantId);
         List<BusinessCategory> categoryFromDb=new ArrayList<>();
         List<Object> preparedStatementValues= new ArrayList<Object>();
         preparedStatementValues.add(name);
         preparedStatementValues.add(tenantId);
         preparedStatementValues.add(id);
         
  	if(isUpdate)
      	  categoryFromDb=jdbcTemplate.query(GET_CATEGORY_BY_NAME_TENANTID_AND_ID,
          		   preparedStatementValues.toArray(),businessCategoryRowMapper);
          else
           categoryFromDb= jdbcTemplate.query(GET_CATEGORY_BY_NAME_AND_TENANTID,
      		   preparedStatementValue.toArray(),businessCategoryRowMapper);
        if(!categoryFromDb.isEmpty())
        return false;
        else
      	  return true;
       }



	public boolean checkCategoryByCodeAndTenantIdExists(String code, String tenantId,Long id,Boolean isUpdate) {
 	   final List<Object> preparedStatementValue = new ArrayList<Object>();
       preparedStatementValue.add(code);
       preparedStatementValue.add(tenantId);
       List<BusinessCategory> categoryFromDb=new ArrayList<>();
       List<Object> preparedStatementValues= new ArrayList<Object>();
       preparedStatementValues.add(code);
       preparedStatementValues.add(tenantId);
       preparedStatementValues.add(id);
       
	if(isUpdate)
    	  categoryFromDb=jdbcTemplate.query(GET_CATEGORY_BY_CODE_TENANTID_AND_ID,
        		   preparedStatementValues.toArray(),businessCategoryRowMapper);
        else
         categoryFromDb= jdbcTemplate.query(GET_CATEGORY_BY_CODE_AND_TENANTID,
    		   preparedStatementValue.toArray(),businessCategoryRowMapper);
      if(!categoryFromDb.isEmpty())
      return false;
      else
    	  return true;
	}



	public BusinessCategory getByIdAndTenantId(Long id, String tenantId) {
		 final List<Object> preparedStatementValues = new ArrayList<Object>();
		preparedStatementValues.add(id);
		preparedStatementValues.add(tenantId);
		List<BusinessCategory> categoryFromDb=jdbcTemplate.query(GET_CATEGORY_BY_ID_AND_TENANTID,preparedStatementValues.toArray(),businessCategoryRowMapper);
		return categoryFromDb.get(0);
	}
}
