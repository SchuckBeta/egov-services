package org.egov.works.workorder.domain.service;

import org.egov.tracer.kafka.LogAwareKafkaTemplate;
import org.egov.works.commons.utils.CommonUtils;
import org.egov.works.workorder.config.PropertiesManager;
import org.egov.works.workorder.domain.repository.builder.IdGenerationRepository;
import org.egov.works.workorder.domain.validator.WorkOrderValidator;
import org.egov.works.workorder.utils.WorkOrderUtils;
import org.egov.works.workorder.web.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ritesh on 27/11/17.
 */
@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderUtils workOrderUtils;

    @Autowired
    private LogAwareKafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private PropertiesManager propertiesManager;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private WorkOrderValidator workOrderValidator;

    @Autowired
    private IdGenerationRepository idGenerationRepository;

    public WorkOrderResponse create(final WorkOrderRequest workOrderRequest) {

        workOrderValidator.validateWorkOrder(workOrderRequest);
        LetterOfAcceptanceResponse letterOfAcceptanceResponse = new LetterOfAcceptanceResponse();
        String departmentCode;
        for (WorkOrder workOrder : workOrderRequest.getWorkOrders()) {
            workOrder.setId(commonUtils.getUUID());
            workOrder.setAuditDetails(workOrderUtils.setAuditDetails(workOrderRequest.getRequestInfo(), false));
            for (WorkOrderDetail workOrderDetail : workOrder.getWorkOrderDetails()) {
                workOrderDetail.setId(commonUtils.getUUID());
                workOrderDetail.setWorkOrder(workOrder.getId());
                workOrderDetail.setAuditDetails(workOrderUtils.setAuditDetails(workOrderRequest.getRequestInfo(), false));
            }

            if (!workOrder.getLetterOfAcceptance().getSpillOverFlag()) {
                departmentCode = workOrderValidator.getLetterOfAcceptanceResponse(workOrderRequest, workOrder).getLetterOfAcceptances().get(0).getLetterOfAcceptanceEstimates().get(0).getDetailedEstimate().getDepartment().getCode();
                String workOrderNumber = idGenerationRepository.generateLOANumber(workOrder.getTenantId(),
                        workOrderRequest.getRequestInfo());
                // TODO: check idgen to accept values to generate
                workOrder.setWorkOrderNumber(propertiesManager.getLoaNumberPrefix() + "/"
                        + departmentCode + workOrderNumber);
            }

        }
        kafkaTemplate.send(propertiesManager.getWorksWorkOrderCreateTopic(), workOrderRequest);
        WorkOrderResponse workOrderResponse = new WorkOrderResponse();
        workOrderResponse.setWorkOrders(workOrderRequest.getWorkOrders());
        workOrderResponse.setResponseInfo(workOrderUtils.getResponseInfo(workOrderRequest.getRequestInfo()));
        return workOrderResponse;
    }

    public WorkOrderResponse update(final WorkOrderRequest workOrderRequest) {
        workOrderValidator.validateWorkOrder(workOrderRequest);
        for (WorkOrder workOrder : workOrderRequest.getWorkOrders()) {
            if (workOrder.getId() == null)
                workOrder.setId(commonUtils.getUUID());
            workOrder.setAuditDetails(workOrderUtils.setAuditDetails(workOrderRequest.getRequestInfo(), true));
            for (WorkOrderDetail workOrderDetail : workOrder.getWorkOrderDetails()) {
                if (workOrderDetail.getId().isEmpty())
                    workOrderDetail.setId(commonUtils.getUUID());
                workOrderDetail.setWorkOrder(workOrder.getId());
                workOrderDetail.setAuditDetails(workOrderUtils.setAuditDetails(workOrderRequest.getRequestInfo(), true));
            }
        }
        kafkaTemplate.send(propertiesManager.getWorksWorkOrderCreateTopic(), workOrderRequest);
        WorkOrderResponse workOrderResponse = new WorkOrderResponse();
        workOrderResponse.setWorkOrders(workOrderRequest.getWorkOrders());
        workOrderResponse.setResponseInfo(workOrderUtils.getResponseInfo(workOrderRequest.getRequestInfo()));
        return workOrderResponse;
    }

    public WorkOrderResponse search(final WorkOrderSearchContract workOrderSearchContract, final RequestInfo requestInfo) {

        WorkOrderResponse workOrderResponse = new WorkOrderResponse();

        return workOrderResponse;
    }
}
