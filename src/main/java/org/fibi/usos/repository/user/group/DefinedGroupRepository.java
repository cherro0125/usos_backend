package org.fibi.usos.repository.user.group;

import org.fibi.usos.model.user.group.DefinedGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface DefinedGroupRepository extends CrudRepository<DefinedGroup,Long> {
    Optional<DefinedGroup> findByName(String name);
    Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId);
}
