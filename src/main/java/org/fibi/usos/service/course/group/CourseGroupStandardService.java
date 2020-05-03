package org.fibi.usos.service.course.group;

import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.fibi.usos.repository.course.group.CourseGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseGroupStandardService implements CourseGroupService {

    private CourseGroupRepository courseGroupRepository;

    public CourseGroupStandardService(CourseGroupRepository courseGroupRepository) {
        this.courseGroupRepository = courseGroupRepository;
    }

    @Override
    public Optional<Collection<CourseGroupModel>> getAllByLeaderId(long leaderId) {
        return courseGroupRepository.getAllByLeaderId(leaderId);
    }

    @Override
    public Optional<CourseGroupModel> findByName(String name) {
        return courseGroupRepository.findByName(name);
    }
}
