package org.fibi.usos.dto.course.degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.CourseResponseDto;
import org.fibi.usos.model.degree.DegreeType;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DegreeCourseResponseDto {
    private String name;
    private String description;
    private Boolean isFullTimeStudies;
    private Integer numberOfSemesters;
    @Enumerated(EnumType.STRING)
    private DegreeType finalDegreeType;
    private Set<CourseResponseDto> courses;
}
