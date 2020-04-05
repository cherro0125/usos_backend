package org.fibi.usos.model.course;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.model.degree.DegreeCourseModel;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Course")
@Table(name = "courses")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class CourseModel extends BaseIdentityModel {
    private String name;
    private String description;
    private Integer ectsPoints;

    @ElementCollection(targetClass = CourseType.class)
    @JoinTable(name = "courses_allowed_types", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "course_type")
    @Enumerated(EnumType.ORDINAL)
    private Set<CourseType> allowedCourseTypes;
    @OneToMany
    private Set<CourseGroupModel> courseGroups;
    @ManyToMany
    private Set<DegreeCourseModel> degreeCourses;
}
