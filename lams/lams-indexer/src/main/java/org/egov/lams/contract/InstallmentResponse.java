package org.egov.lams.contract;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@ToString
public class InstallmentResponse {
	@JsonProperty("ResponseInfo")
	ResponseInfo responseInfo;

	@JsonProperty("Installments")
	private List<Installment> installments = new ArrayList<Installment>();
}