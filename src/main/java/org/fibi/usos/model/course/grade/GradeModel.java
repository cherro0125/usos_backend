package org.fibi.usos.model.course.grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.grade.GradeDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.model.exam.ExamDateType;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Grade")
@Table(name = "grades")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class GradeModel extends BaseIdentityModel {
    private String description;
    @Enumerated(EnumType.STRING)
    private GradeValueType value;
    @Enumerated(EnumType.ORDINAL)
    private ExamDateType examDateType;
    @ManyToOne
    private CourseGroupModel courseGroup;
    @ManyToOne
    private UserModel assignedUser;
    @ManyToOne
    private UserModel createdBy;

    public GradeDto MapToDto() {
        GradeDto dto = new GradeDto();
        dto.setDescription(getDescription());
        dto.setValue(getValue());
        dto.setExamDateType(getExamDateType());
        dto.setCourseGroup(getCourseGroup());
        //dto.setAssignedUser(getAssignedUser());

    }
}
