package org.egov.works.workflow.repository;

import org.egov.works.workflow.contracts.ProcessInstanceRequest;
import org.egov.works.workflow.contracts.ProcessInstanceResponse;
import org.egov.works.workflow.contracts.TaskRequest;
import org.egov.works.workflow.contracts.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkflowRepository {

	private RestTemplate restTemplate;
	private String startWorkflowUrl;
	private String updateWorkflowUrl;

	@Autowired
	public WorkflowRepository(@Value("${egov.services.common_workflow.hostname}") String commonWorkflowHostname,
			@Value("${egov.services.common_workflow.start_workflow}") String startPath,
			@Value("${egov.services.common_workflow.update_workflow}") String updatePath, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.startWorkflowUrl = commonWorkflowHostname + startPath;
		this.updateWorkflowUrl = commonWorkflowHostname + updatePath;
	}

	public ProcessInstanceResponse start(final ProcessInstanceRequest processInstanceRequest) {

		final HttpEntity<ProcessInstanceRequest> request = new HttpEntity<>(processInstanceRequest);

		System.out.println(
				"Works-Workflow start senderName" + processInstanceRequest.getProcessInstance().getSenderName());

		return restTemplate.postForObject(startWorkflowUrl, request, ProcessInstanceResponse.class);
	}

	public TaskResponse update(final TaskRequest taskRequest) {

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		final HttpEntity<TaskRequest> request = new HttpEntity<>(taskRequest);

		return restTemplate.postForObject(updateWorkflowUrl.replaceAll("\\{id\\}", taskRequest.getTask().getId()),
				request, TaskResponse.class);
	}

}
