package org.fibi.usos.service.course.grade;

import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.model.course.grade.GradeModel;

import java.util.Collection;
import java.util.Optional;

public interface GradeService {
    Optional<GradeModel> createOrUpdate(GradeChangeRequestDto grade);
    Optional<GradeModel> findGradeById(Long id);
    Optional<Collection<GradeModel>> findGradesByAssignedUserId(Long assignedUserId);
    Optional<Collection<GradeModel>> findGradesByCreatedByUserId(Long createdByUserId);
}
