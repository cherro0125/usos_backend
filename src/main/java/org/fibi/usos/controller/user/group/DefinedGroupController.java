package org.fibi.usos.controller.user.group;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.dto.user.group.DefinedGroupResponseDto;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.service.user.group.DefinedGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/definedGroups")
public class DefinedGroupController {
    private DefinedGroupService definedGroupService;

    public DefinedGroupController(DefinedGroupService definedGroupService) {
        this.definedGroupService = definedGroupService;
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping(value = "/{degreeCourseId}")
    public ResponseEntity<Collection<DefinedGroupResponseDto>> getDefinedGroupByDegreeCourseId(@PathVariable("degreeCourseId") long degreeCourseId) {
        Optional<Collection<DefinedGroup>> definedGroups = definedGroupService.getAllByDegreeCourseId(degreeCourseId);
        return definedGroups.<ResponseEntity<Collection<DefinedGroupResponseDto>>>map(definedGroupsModels -> ResponseEntity.ok(definedGroupsModels.stream().map(DefinedGroup::mapToResponseDto).collect(Collectors.toSet()))).orElseGet(() -> ResponseEntity.ok(null));
    }
    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @PostMapping("/add")
    public ResponseEntity<DefinedGroupResponseDto> createDefinedGroupInCourse(@RequestBody DefinedGroupRequestDto dto) {
        DefinedGroupResponseDto res = new DefinedGroupResponseDto();
        Optional<DefinedGroup> model = definedGroupService.create(dto);
        if(model.isPresent()){
            res = model.get().mapToResponseDto();
        }
        return ResponseEntity.ok(res);
    }
}
