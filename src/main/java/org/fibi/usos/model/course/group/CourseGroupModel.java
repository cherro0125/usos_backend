package org.fibi.usos.model.course.group;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.CourseType;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "CourseGroup")
@Table(name = "course_groups")
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
}
