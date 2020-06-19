package org.fibi.usos.service.degree;

import org.fibi.usos.dto.course.degree.DegreeCourseRequestDto;
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

    @Override
    public Optional<DegreeCourseModel> create(DegreeCourseRequestDto course) {
        DegreeCourseModel c = new DegreeCourseModel();
        c.setName(course.getName());
        c.setDescription(course.getDescription());
        c.setIsFullTimeStudies(course.getIsFullTimeStudies());
        c.setNumberOfSemesters(course.getNumberOfSemesters());
        c.setFinalDegreeType(course.getFinalDegreeType());
        return Optional.of(degreeCourseRepository.save(c));
    }
}
