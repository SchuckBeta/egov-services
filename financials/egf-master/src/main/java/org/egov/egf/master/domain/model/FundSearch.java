package org.egov.egf.master.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundSearch extends Fund {
	private Date fromDate;
	private int pageSize=10;
	private String sortBy;
}
