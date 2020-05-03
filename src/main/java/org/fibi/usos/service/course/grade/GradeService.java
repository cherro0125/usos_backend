package org.fibi.usos.service.course.grade;

import org.fibi.usos.model.course.grade.GradeModel;

import java.util.Optional;

public interface GradeService {
    Optional<GradeModel> addNewGrade(GradeModel grade);
}
