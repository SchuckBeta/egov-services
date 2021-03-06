package org.egov.pa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KpiCategory {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("code")
	private String code; 
	
	@JsonProperty("tenantId")
	private String tenantId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	

}
