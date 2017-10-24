package org.egov.pgrrest.read.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TopComplaintTypesResponse {

    private Integer count;

    private String complaintTypeName;

    private String month;

    private String code;

    public org.egov.pgrrest.read.web.contract.TopComplaintTypesResponse toContract(){
        return org.egov.pgrrest.read.web.contract.TopComplaintTypesResponse.builder()
               .count(count)
               .complaintTypeName(complaintTypeName)
               .build();
    }

    public org.egov.pgrrest.read.web.contract.TopComplaintTypesResponse toTopFiveComplaintTypesContract(){
        return org.egov.pgrrest.read.web.contract.TopComplaintTypesResponse.builder()
            .count(count)
            .complaintTypeName(complaintTypeName)
            .month(month)
            .code(code)
            .build();
    }
}
