package org.fibi.usos.model.degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.CourseModel;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DegreeCourse")
@Table(name = "degree_courses")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class DegreeCourseModel extends BaseIdentityModel {
    private String name;
    private String description;
    private Boolean isFullTimeStudies;
    private Integer numberOfSemesters;
    @Enumerated(EnumType.STRING)
    private DegreeType finalDegreeType;

    @ManyToMany
    private Set<CourseModel> courses;
}
