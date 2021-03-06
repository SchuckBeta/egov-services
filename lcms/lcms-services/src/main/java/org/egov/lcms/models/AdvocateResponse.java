package org.egov.lcms.models;

import java.util.List;

import org.egov.common.contract.response.ResponseInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @author Yosadhara 
 * This object holds information about the Advocate Response
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvocateResponse {

	@JsonProperty("ResponseInfo")
	private ResponseInfo responseInfo = null;

	@JsonProperty("advocates")
	private List<Advocate> advocates = null;
}
