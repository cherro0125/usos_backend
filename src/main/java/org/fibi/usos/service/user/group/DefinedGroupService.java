package org.fibi.usos.service.user.group;

import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.model.user.group.DefinedGroup;

import java.util.Collection;
import java.util.Optional;

public interface DefinedGroupService {
    Optional<DefinedGroup> findByName(String name);
    Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId);
    Optional<DefinedGroup> create(DefinedGroupRequestDto course);
}
