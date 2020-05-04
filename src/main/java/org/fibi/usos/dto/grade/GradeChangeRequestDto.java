package org.fibi.usos.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.course.grade.GradeValueType;
import org.fibi.usos.model.exam.ExamDateType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeChangeRequestDto {
    private String description;
    @Enumerated(EnumType.STRING)
    private GradeValueType value;
    @Enumerated(EnumType.ORDINAL)
    private ExamDateType examDateType;
    private long courseGroupId;
    private long assignedUserId;
    private long createdById;
}
