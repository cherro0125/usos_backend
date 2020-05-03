package org.fibi.usos.model.course.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.course.group.CourseGroupResponseDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.CourseType;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "CourseGroup")
@Table(name = "course_groups")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class CourseGroupModel extends BaseIdentityModel {
    private String name;
    @OneToOne
    private UserModel leader;
    @ManyToMany
    private Set<UserModel> students;
    @Enumerated(EnumType.STRING)
    private CourseType courseType;
    @ManyToOne
    private CourseModel course;

    public CourseGroupResponseDto mapToResponseDto(){
        CourseGroupResponseDto responseDto = new CourseGroupResponseDto();
        responseDto.setCourse(course.mapToResponseDto());
        responseDto.setCourseType(courseType);
        responseDto.setLeader(leader.mapToDto());
        responseDto.setName(name);
        responseDto.setStudents(students.stream().map(UserModel::mapToDto).collect(Collectors.toSet()));
        return responseDto;
    }
}
