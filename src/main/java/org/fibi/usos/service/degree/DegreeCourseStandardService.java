package org.fibi.usos.service.degree;

import org.fibi.usos.dto.course.degree.DegreeCourseRequestDto;
import org.fibi.usos.model.degree.DegreeCourseModel;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.fibi.usos.repository.user.group.DefinedGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class DegreeCourseStandardService implements DegreeCourseService{
    private DegreeCourseRepository degreeCourseRepository;
    private DefinedGroupRepository definedGroupRepository;

    public DegreeCourseStandardService(DegreeCourseRepository degreeCourseRepository, DefinedGroupRepository definedGroupRepository) {
        this.degreeCourseRepository = degreeCourseRepository;
        this.definedGroupRepository = definedGroupRepository;
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
    public boolean delete(Long id) {
        if(degreeCourseRepository.findById(id).isPresent()){
            if(definedGroupRepository.findByDegreeCourseId(id).isPresent()){
                Collection<DefinedGroup> groups =definedGroupRepository.findByDegreeCourseId(id).get();
                for(DefinedGroup group : groups){
                    definedGroupRepository.deleteById(group.getId());
                }
            }
            degreeCourseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
