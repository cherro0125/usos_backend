package org.fibi.usos.repository.grade;

import com.sun.xml.bind.v2.model.core.ID;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<GradeModel, ID> {
    Optional<GradeModel> findGradeModelById(Long id);
}
