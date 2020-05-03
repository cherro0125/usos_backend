package org.fibi.usos.listener.seeder.course.degree;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.degree.DegreeType;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.logging.Logger;

@Component
public class DegreeCourseSeeder implements Seeder {

    private static final Logger logger  = Logger.getLogger(DegreeCourseSeeder.class.getName());
    private DegreeCourseRepository degreeCourseRepository;

    public DegreeCourseSeeder(DegreeCourseRepository degreeCourseRepository) {
        this.degreeCourseRepository = degreeCourseRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        DegreeCourseModel degreeOne = new DegreeCourseModel("Informatyka","Lorem ipsum informaticum dolores",true,7, DegreeType.BACHELOR, Collections.emptySet());
        degreeCourseRepository.save(degreeOne);
        logger.info("Create new degree course.");
    }
}