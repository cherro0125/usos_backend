package org.fibi.usos.model.degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.controller.course.degree.DegreeCourseResponseDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.CourseModel;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "degreecourse_course",
            joinColumns = @JoinColumn(name = "degree_course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<CourseModel> courses;

    public DegreeCourseResponseDto mapToResponseDto(){
        DegreeCourseResponseDto responseDto = new DegreeCourseResponseDto();
        responseDto.setDescription(description);
        responseDto.setFinalDegreeType(finalDegreeType);
        responseDto.setCourses(courses.stream().map(CourseModel::mapToResponseDto).collect(Collectors.toSet()));
        responseDto.setIsFullTimeStudies(isFullTimeStudies);
        responseDto.setName(name);
        responseDto.setNumberOfSemesters(numberOfSemesters);
        return responseDto;
    }
}
