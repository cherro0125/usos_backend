package org.fibi.usos.listener.seeder.user.group;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.listener.seeder.course.CourseSeeder;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.fibi.usos.repository.user.group.DefinedGroupRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class DefinedGroupSeeder implements Seeder {
    private DegreeCourseRepository degreeCourseRepository;
    private DefinedGroupRepository definedGroupRepository;
    private UserRepository userRepository;

    private static final Logger logger  = Logger.getLogger(CourseSeeder.class.getName());

    public DefinedGroupSeeder(DegreeCourseRepository degreeCourseRepository,DefinedGroupRepository definedGroupRepository, UserRepository userRepository) {
        this.degreeCourseRepository=degreeCourseRepository;
        this.definedGroupRepository=definedGroupRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        Optional<DegreeCourseModel> degreeCourseModel = degreeCourseRepository.findByName("Informatyka");
        Optional<Collection<UserModel>> student = userRepository.findAllByRole(UserRole.STUDENT);
        Set<UserModel> students = new HashSet<>(student.get());
        DefinedGroup definedGroup = new DefinedGroup("3ID12A","Grupa 312A",degreeCourseModel.get(),students);

        definedGroupRepository.save(definedGroup);
        logger.info("Create new defined group");

    }
}
