package org.fibi.usos.service.user.group;

import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.fibi.usos.repository.user.group.DefinedGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefinedGroupStandardService implements DefinedGroupService{
    private DefinedGroupRepository definedGroupRepository;
    private DegreeCourseRepository degreeCourseRepository;

    public DefinedGroupStandardService(DefinedGroupRepository definedGroupRepository, DegreeCourseRepository degreeCourseRepository) {
        this.definedGroupRepository = definedGroupRepository;
        this.degreeCourseRepository = degreeCourseRepository;
    }

    @Override
    public Optional<DefinedGroup> findByName(String name) {
        return definedGroupRepository.findByName(name);
    }

    @Override
    public Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId) {
        return definedGroupRepository.getAllByDegreeCourseId(degreeCourseId);
    }

    @Override
    public Optional<DefinedGroup> create(DefinedGroupRequestDto dto) {
        DefinedGroup d = new DefinedGroup();
        d.setName(dto.getName());
        d.setDescription(dto.getDescription());
        degreeCourseRepository.findById(dto.getDegreeCourseId()).ifPresent(d::setDegreeCourse);
        return Optional.of(definedGroupRepository.save(d));
    }
}
