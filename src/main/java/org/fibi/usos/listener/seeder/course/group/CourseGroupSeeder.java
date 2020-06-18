package org.fibi.usos.listener.seeder.course.group;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.CourseType;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.pool.RegisteredEnumPool;
import org.fibi.usos.repository.course.CourseRepository;
import org.fibi.usos.repository.course.group.CourseGroupRepository;
import org.fibi.usos.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CourseGroupSeeder  implements Seeder {
    private UserService userService;
    private CourseRepository courseRepository;
    private CourseGroupRepository courseGroupRepository;
    private static final Logger logger  = LoggerFactory.getLogger(CourseGroupSeeder.class);

    public CourseGroupSeeder(UserService userService, CourseRepository courseRepository, CourseGroupRepository courseGroupRepository) {
        this.userService = userService;
        this.courseRepository = courseRepository;
        this.courseGroupRepository = courseGroupRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        Optional<UserModel> leader = userService.findByUsername("lecturer");
        Optional<UserModel> student = userService.findByUsername("student");
        Set<UserModel> students =  new HashSet<>(Arrays.asList(student.get()));
        Optional<CourseModel> course = courseRepository.findByName("PAI2");

        CourseGroupModel model = new CourseGroupModel("Projekt PAI2 3ID12A",leader.get(),students, CourseType.PROJECT,course.get());
        courseGroupRepository.save(model);
        logger.info("Create course group.");
    }
}
