package org.fibi.usos.repository.course.grade;

import com.sun.xml.bind.v2.model.core.ID;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.exam.ExamDateType;
import org.fibi.usos.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<GradeModel, Long> {
    Optional<GradeModel> findGradeModelById(Long id);
    Optional<GradeModel> findGradeModelByCourseGroupIdAndAssignedUserIdAndExamDateType(Long courseGroupId, Long assignedUserId, ExamDateType examDateType);
    Optional<Collection<GradeModel>> findAllByAssignedUserId(Long assignedUserId);
    Optional<Collection<GradeModel>> findAllByCreatedById(Long createdByUserId);
}
