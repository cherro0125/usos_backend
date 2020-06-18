package org.fibi.usos.listener.seeder.course;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.CourseType;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.pool.RegisteredEnumPool;
import org.fibi.usos.repository.course.CourseRepository;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CourseSeeder implements Seeder {
    private DegreeCourseRepository degreeCourseRepository;
    private CourseRepository courseRepository;
    private static final Logger logger  = LoggerFactory.getLogger(CourseSeeder.class);

    public CourseSeeder(DegreeCourseRepository degreeCourseRepository, CourseRepository courseRepository) {
        this.degreeCourseRepository = degreeCourseRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        Optional<DegreeCourseModel> degreeCourseModel = degreeCourseRepository.findByName("Informatyka");
        Set<CourseType> allowedTypes = new HashSet<>(Arrays.asList(CourseType.PROJECT,CourseType.LECTURE));
        Set<DegreeCourseModel> degreeCourseModels = new HashSet<>(Arrays.asList(degreeCourseModel.get()));

        CourseModel courseModel = new CourseModel("PAI2","Lorem ipsum bere bere",21,allowedTypes, Collections.emptySet(),degreeCourseModels);
        courseRepository.save(courseModel);
        logger.info("Create new course");

    }
}
