package org.fibi.usos.service.degree;

import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DegreeCourseStandardService implements DegreeCourseService{
    private DegreeCourseRepository degreeCourseRepository;

    public DegreeCourseStandardService(DegreeCourseRepository degreeCourseRepository) {
        this.degreeCourseRepository = degreeCourseRepository;
    }

    @Override
    public Optional<DegreeCourseModel> getById(Long id) {
        return degreeCourseRepository.findById(id);
    }

    @Override
    public Optional<Iterable<DegreeCourseModel>> getAll() {
        Iterable<DegreeCourseModel> models = degreeCourseRepository.findAll();
        return models.iterator().hasNext() ? Optional.of(models) : Optional.empty();
    }

}
