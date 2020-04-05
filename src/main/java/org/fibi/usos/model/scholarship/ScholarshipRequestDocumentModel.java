package org.fibi.usos.model.scholarship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.document.DocumentStatusType;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "ScholarshipRequest")
@Table(name = "scholarship_request")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class ScholarshipRequestDocumentModel extends BaseIdentityModel {
    @ManyToOne
    private UserModel applicant;
    @ManyToOne
    private UserModel approver;
    @Enumerated(EnumType.STRING)
    private ScholarshipType scholarshipType;
    @Enumerated(EnumType.STRING)
    private DocumentStatusType status;
    private float averageFromLastAcademicYear;
}
