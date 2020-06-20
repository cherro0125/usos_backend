package org.fibi.usos.model.user.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.degree.DegreeCourseResponseDto;
import org.fibi.usos.dto.user.group.DefinedGroupResponseDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DefinedGroup")
@Table(name = "defined_groups")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class DefinedGroup extends BaseIdentityModel {
    private String name;
    private String description;
    @ManyToOne
    private DegreeCourseModel degreeCourse;

    @ManyToMany
    private Set<UserModel> students;

    public DefinedGroupResponseDto mapToResponseDto(){
        DefinedGroupResponseDto responseDto = new DefinedGroupResponseDto();
        responseDto.setId(getId());
        responseDto.setName(name);
        responseDto.setDescription(description);
        responseDto.setDegreeCourseModel(degreeCourse);
        if(students!=null)
            responseDto.setStudents(students.stream().map(UserModel::mapToDto).collect(Collectors.toSet()));
        return responseDto;
    }
}
