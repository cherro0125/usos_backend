package org.fibi.usos.service.degree;

import org.fibi.usos.model.degree.DegreeCourseModel;

import java.util.Optional;

public interface DegreeCourseService {
    Optional<DegreeCourseModel> getById(Long id);
    Optional<Iterable<DegreeCourseModel>> getAll();
}
