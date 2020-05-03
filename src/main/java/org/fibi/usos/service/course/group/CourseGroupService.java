package org.fibi.usos.service.course.group;

import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.group.CourseGroupModel;

import java.util.Collection;
import java.util.Optional;

public interface CourseGroupService {
    Optional<Collection<CourseGroupModel>> getAllByLeaderId(long leaderId);
    Optional<CourseGroupModel> findByName(String name);
}
