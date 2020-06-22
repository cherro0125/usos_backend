package org.fibi.usos.service.user.group;

import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.dto.user.group.DefinedGroupStudentsRequestDto;
import org.fibi.usos.model.user.group.DefinedGroup;

import java.util.Collection;
import java.util.Optional;

public interface DefinedGroupService {
    Optional<DefinedGroup> findByName(String name);
    Optional<DefinedGroup> findById(Long id);
    Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId);
    Optional<DefinedGroup> create(DefinedGroupRequestDto course);
    Optional<Iterable<DefinedGroup>> getAll();
    Optional<DefinedGroup> addStudent(DefinedGroupStudentsRequestDto dto);
    boolean delete(Long id);
    boolean removeStudent(Long id, Long studentId);
}
