package org.egov.lcms.workflow.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * WorkflowDetailsRequestInfo class
 * 
 * @author Yosadhara
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkflowDetailsRequestInfo {

	private String tenantId;

	private RequestInfo requestInfo;

	private WorkFlowDetails workflowDetails;
}