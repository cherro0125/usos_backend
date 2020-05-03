package org.fibi.usos.repository.course.group;

import org.fibi.usos.model.course.CourseModel;
import org.fibi.usos.model.course.group.CourseGroupModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CourseGroupRepository extends CrudRepository<CourseGroupModel, Long> {
    Optional<Collection<CourseGroupModel>> getAllByLeaderId(long leaderId);
    Optional<CourseGroupModel> findByName(String name);
}
