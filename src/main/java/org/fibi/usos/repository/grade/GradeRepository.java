package org.fibi.usos.repository.grade;

import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<GradeModel, Long> {
    Optional<GradeModel> findGradeModelByAssignedUser(UserModel assignedUser);
}
