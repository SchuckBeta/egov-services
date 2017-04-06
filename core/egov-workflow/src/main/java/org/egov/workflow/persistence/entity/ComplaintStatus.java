package org.egov.workflow.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.egov.workflow.persistence.entity.ComplaintStatus.SEQ_COMPLAINTSTATUS;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "complaintstatus_write")
@Table(name = "egpgr_complaintstatus")
@SequenceGenerator(name = SEQ_COMPLAINTSTATUS, sequenceName = SEQ_COMPLAINTSTATUS, allocationSize = 1)
public class ComplaintStatus extends AbstractPersistable<Long> {
    public static final String SEQ_COMPLAINTSTATUS = "SEQ_EGPGR_COMPLAINTSTATUS";
    private static final long serialVersionUID = -9009821412847211632L;
    @Id
    @GeneratedValue(generator = SEQ_COMPLAINTSTATUS, strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    public org.egov.workflow.domain.model.ComplaintStatus toDomain() {
        return new org.egov.workflow.domain.model.ComplaintStatus(id, name);
    }
}
