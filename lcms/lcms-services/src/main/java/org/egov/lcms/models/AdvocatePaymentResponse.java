package org.egov.lcms.models;

import java.util.List;

import org.egov.common.contract.response.ResponseInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * @author Shubham 
 * This object holds information about the Advocate Payment Response
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvocatePaymentResponse {
	@JsonProperty("ResponseInfo")
	private ResponseInfo responseInfo = null;

	@JsonProperty("advocatePayments")
	private List<AdvocatePayment> advocatePayments = null;
}
