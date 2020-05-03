package org.fibi.usos.listener.seeder;

import org.fibi.usos.listener.seeder.course.CourseSeeder;
import org.fibi.usos.listener.seeder.course.degree.DegreeCourseSeeder;
import org.fibi.usos.listener.seeder.course.grade.GradeSeeder;
import org.fibi.usos.listener.seeder.course.group.CourseGroupSeeder;
import org.fibi.usos.listener.seeder.user.UserSeeder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GeneralSeeder implements Seeder {
    private UserSeeder userSeeder;
    private DegreeCourseSeeder degreeCourseSeeder;
    private CourseSeeder courseSeeder;
    private CourseGroupSeeder courseGroupSeeder;
    private GradeSeeder gradeSeeder;

    public GeneralSeeder(UserSeeder userSeeder, DegreeCourseSeeder degreeCourseSeeder, CourseSeeder courseSeeder, CourseGroupSeeder courseGroupSeeder,GradeSeeder gradeSeeder) {
        this.userSeeder = userSeeder;
        this.degreeCourseSeeder = degreeCourseSeeder;
        this.courseSeeder = courseSeeder;
        this.courseGroupSeeder = courseGroupSeeder;
        this.gradeSeeder = gradeSeeder;
    }

    @Override
    public void init() {

    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void seed() {
        userSeeder.seed();
        degreeCourseSeeder.seed();
        courseSeeder.seed();
        courseGroupSeeder.seed();
        gradeSeeder.seed();
    }
}
