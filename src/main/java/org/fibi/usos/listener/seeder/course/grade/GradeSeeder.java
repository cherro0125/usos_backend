package org.fibi.usos.listener.seeder.course.grade;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.listener.seeder.course.group.CourseGroupSeeder;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.course.grade.GradeValueType;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.model.exam.ExamDateType;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.course.grade.GradeRepository;
import org.fibi.usos.service.course.group.CourseGroupService;
import org.fibi.usos.service.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class GradeSeeder implements Seeder {

    private UserService userService;
    private GradeRepository gradeRepository;
    private CourseGroupService courseGroupService;
    private static final Logger logger  = Logger.getLogger(CourseGroupSeeder.class.getName());

    public GradeSeeder(UserService userService,GradeRepository gradeRepository, CourseGroupService courseGroupService){
        this.userService = userService;
        this.gradeRepository = gradeRepository;
        this.courseGroupService = courseGroupService;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        Optional<UserModel> lecturer = userService.findByUsername("lecturer");
        Optional<UserModel> student = userService.findByUsername("student");
        Optional<CourseGroupModel> courseGroup = courseGroupService.findByName("Projekt PAI2 3ID12A");

        GradeModel gradeModel = new GradeModel("First exam", GradeValueType.GOOD, ExamDateType.FIRST,courseGroup.get(),student.get(),lecturer.get());
        gradeRepository.save(gradeModel);
        logger.info("Create grade.");
    }
}
