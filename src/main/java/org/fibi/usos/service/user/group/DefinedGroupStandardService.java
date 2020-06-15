package org.fibi.usos.service.user.group;

import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.repository.user.group.DefinedGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefinedGroupStandardService implements DefinedGroupService{
    private DefinedGroupRepository definedGroupRepository;

    public DefinedGroupStandardService(DefinedGroupRepository definedGroupRepository) {
        this.definedGroupRepository = definedGroupRepository;
    }

    @Override
    public Optional<DefinedGroup> findByName(String name) {
        return definedGroupRepository.findByName(name);
    }

    @Override
    public Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId) {
        return definedGroupRepository.getAllByDegreeCourseId(degreeCourseId);
    }
}
