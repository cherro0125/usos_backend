package org.fibi.usos.repository.course.degree;

import org.fibi.usos.model.degree.DegreeCourseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeCourseRepository extends CrudRepository<DegreeCourseModel,Long> {
        Optional<DegreeCourseModel> findByName(String name);
}
