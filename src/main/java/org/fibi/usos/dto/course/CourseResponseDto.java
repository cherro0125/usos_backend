package org.fibi.usos.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.degree.DegreeCourseResponseDto;
import org.fibi.usos.model.course.CourseType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer ectsPoints;
    @Enumerated(EnumType.ORDINAL)
    private Set<CourseType> allowedCourseTypes;
    private Set<DegreeCourseResponseDto> degreeCourses;
}
