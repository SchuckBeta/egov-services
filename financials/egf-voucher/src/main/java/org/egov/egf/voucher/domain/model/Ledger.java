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
 *         3) This license does not grant any rights to any Long of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.egf.voucher.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.egov.common.domain.model.Auditable;
import org.egov.egf.master.web.contract.ChartOfAccountContract;
import org.egov.egf.master.web.contract.FunctionContract;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = { "chartOfAccount", "function" }, callSuper = false)
public class Ledger extends Auditable {
    private String id;
    /**
     * orderId refers to the order in which account heads are created. This
     * field is used to send back the result in same order as created.
     */
    private Integer orderId;
    @NotNull
    private ChartOfAccountContract chartOfAccount;
    /**
     * glcode refers to the unique account code .
     */
    @NotNull
    @Length(max = 16)
    private String glcode;
    /**
    * debitAmount - this field will have value only if the amount is debited.
    * No negetive amount allowed
    */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    private BigDecimal debitAmount;
    /**
     * creaditAmount - this field will have value only if the amount is debited.
     * No negetive amount allowed
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    private BigDecimal creditAmount;
    private FunctionContract function;
    private Set<SubLedger> subLedgers = new HashSet<SubLedger>();
 

}
