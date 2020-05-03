package org.fibi.usos.repository.course;

import org.fibi.usos.model.course.CourseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<CourseModel,Long> {
    Optional<CourseModel> findByName(String name);
}
