package org.fibi.usos.controller.user.group;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.dto.user.group.DefinedGroupResponseDto;
import org.fibi.usos.dto.user.group.DefinedGroupStudentsRequestDto;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.service.user.group.DefinedGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<DefinedGroupResponseDto>> getAll() {
        List<DefinedGroupResponseDto> res = new LinkedList<>();
        Optional<Iterable<DefinedGroup>> models = definedGroupService.getAll();
        models.ifPresent( it -> it.forEach(i -> res.add(i.mapToResponseDto())));
        return ResponseEntity.ok(res);
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
    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @PostMapping("/assignStudents")
    public ResponseEntity<DefinedGroupResponseDto> createDefinedGroupInCourse(@RequestBody DefinedGroupStudentsRequestDto dto) {
        DefinedGroupResponseDto res = new DefinedGroupResponseDto();
        Optional<DefinedGroup> model = definedGroupService.addStudent(dto);
        if(model.isPresent()){
            res = model.get().mapToResponseDto();
        }
        return ResponseEntity.ok(res);
    }
}
