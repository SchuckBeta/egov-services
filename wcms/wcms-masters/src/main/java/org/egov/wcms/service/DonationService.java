/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.wcms.service;

import java.util.List;

import org.egov.tracer.kafka.LogAwareKafkaTemplate;
import org.egov.wcms.config.PropertiesManager;
import org.egov.wcms.model.Donation;
import org.egov.wcms.repository.DonationRepository;
import org.egov.wcms.web.contract.DonationGetRequest;
import org.egov.wcms.web.contract.DonationRequest;
import org.egov.wcms.web.contract.PropertyTypeResponse;
import org.egov.wcms.web.contract.UsageTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private LogAwareKafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private PropertiesManager propertiesManager;

    @Autowired
    private RestPropertyTaxMasterService restPropertyTaxMasterService;

    public DonationRequest create(final DonationRequest donationRequest) {
        return donationRepository.persistDonationDetails(donationRequest);
    }

    public DonationRequest update(final DonationRequest donationRequest) {
        return donationRepository.persistModifyDonationDetails(donationRequest);
    }

    public Donation sendMessage(final String topic, final String key, final DonationRequest donationRequest) {
        try {
            kafkaTemplate.send(topic, key, donationRequest);
        } catch (final Exception ex) {
            log.error("Exception Encountered : " + ex);
        }
        return donationRequest.getDonation();
    }

    public List<Donation> getDonationList(final DonationGetRequest donationGetRequest) {
        if (donationGetRequest.getPropertyType() != null) {
            final PropertyTypeResponse propertyType = getPropertyIdFromPTModule(
                    donationGetRequest.getPropertyType(), donationGetRequest.getTenantId());
            if (propertyType != null)
                donationGetRequest.setPropertyTypeId(propertyType.getPropertyTypes().get(0).getId());

        }
        if (donationGetRequest.getUsageType() != null) {
            final UsageTypeResponse usageType = getUsageIdFromPTModule(
                    donationGetRequest.getUsageType(), donationGetRequest.getTenantId());
            if (usageType != null)
                donationGetRequest.setUsageTypeId(usageType.getUsageMasters().get(0).getId());

        }
        return donationRepository.findForCriteria(donationGetRequest);
    }

    public Boolean getPropertyTypeByName(final DonationRequest donationRequest) {
        Boolean isValidProperty = Boolean.FALSE;

        final PropertyTypeResponse propertyType = getPropertyIdFromPTModule(
                donationRequest.getDonation().getPropertyType(),
                donationRequest.getDonation().getTenantId());
        if (propertyType.getPropertyTypesSize()) {
            isValidProperty = Boolean.TRUE;
            donationRequest.getDonation().setPropertyTypeId(
                    propertyType.getPropertyTypes() != null && propertyType.getPropertyTypes().get(0) != null
                            ? propertyType.getPropertyTypes().get(0).getId() : "");

        }
        return isValidProperty;

    }

    private PropertyTypeResponse getPropertyIdFromPTModule(final String propertyTypeName, final String tenantId) {
        String url = propertiesManager.getPropertTaxServiceBasePathTopic()
                + propertiesManager.getPropertyTaxServicePropertyTypeSearchPathTopic();
        url = url.replace("{name}", propertyTypeName);
        url = url.replace("{tenantId}", tenantId);
        final PropertyTypeResponse propertyTypes = restPropertyTaxMasterService.getPropertyTypes(url);
        return propertyTypes;
    }

    public Boolean getUsageTypeByName(final DonationRequest donationRequest) {
        Boolean isValidUsage = Boolean.FALSE;
        final UsageTypeResponse usageType = getUsageIdFromPTModule(
                donationRequest.getDonation().getUsageType(),
                donationRequest.getDonation().getTenantId());
        if (usageType.getUsageTypesSize()) {
            isValidUsage = Boolean.TRUE;
            donationRequest.getDonation()
                    .setUsageTypeId(usageType.getUsageMasters() != null && usageType.getUsageMasters().get(0) != null
                            ? usageType.getUsageMasters().get(0).getId() : "");

        }
        return isValidUsage;

    }

    private UsageTypeResponse getUsageIdFromPTModule(final String usageTypeName, final String tenantId) {
        String url = propertiesManager.getPropertTaxServiceBasePathTopic()
                + propertiesManager.getPropertyTaxServiceUsageTypeSearchPathTopic();
        url = url.replace("{name}", usageTypeName);
        url = url.replace("{tenantId}", tenantId);
        final UsageTypeResponse usageType = restPropertyTaxMasterService.getUsageTypes(url);
        return usageType;
    }

}
