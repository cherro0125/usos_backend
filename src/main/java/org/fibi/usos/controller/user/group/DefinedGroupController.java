package org.fibi.usos.controller.user.group;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.user.group.DefinedGroupResponseDto;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.service.user.group.DefinedGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DefinedGroupController {
    private DefinedGroupService definedGroupService;

    public DefinedGroupController(DefinedGroupService definedGroupService) {
        this.definedGroupService = definedGroupService;
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping(value = "/definedGroups/{degreeCourseId}")
    public ResponseEntity<Collection<DefinedGroupResponseDto>> getDefinedGroupByDegreeCourseId(@PathVariable("degreeCourseId") long degreeCourseId) {
        Optional<Collection<DefinedGroup>> definedGroups = definedGroupService.getAllByDegreeCourseId(degreeCourseId);
        return definedGroups.<ResponseEntity<Collection<DefinedGroupResponseDto>>>map(definedGroupsModels -> ResponseEntity.ok(definedGroupsModels.stream().map(DefinedGroup::mapToResponseDto).collect(Collectors.toSet()))).orElseGet(() -> ResponseEntity.ok(null));
    }
}
