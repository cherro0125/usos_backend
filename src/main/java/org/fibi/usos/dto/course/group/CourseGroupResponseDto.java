package org.fibi.usos.dto.course.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.CourseResponseDto;
import org.fibi.usos.dto.user.UserDto;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.CourseType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseGroupResponseDto {
    private String name;
    private UserDto leader;
    private Set<UserDto> students;
    @Enumerated(EnumType.STRING)
    private CourseType courseType;
    private CourseResponseDto course;
}
