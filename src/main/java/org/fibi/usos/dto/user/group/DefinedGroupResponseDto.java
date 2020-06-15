package org.fibi.usos.dto.user.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.user.UserDto;
import org.fibi.usos.model.degree.DegreeCourseModel;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinedGroupResponseDto {
    private Long id;
    private String name;
    private String description;
    private DegreeCourseModel degreeCourseModel;
    private Set<UserDto> students;
}
