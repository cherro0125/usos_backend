package org.fibi.usos.service.course.grade;

import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserModel;

import java.util.Optional;

public interface GradeService {
    Optional<GradeModel> addNewGrade(GradeModel newGrade);
    Optional<GradeModel> findStudentById(Long id);

}
