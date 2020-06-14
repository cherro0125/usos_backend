package org.fibi.usos.controller.course.grade;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.dto.grade.GradeDto;
import org.fibi.usos.entity.response.standard.StandardMessageResponse;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.course.grade.GradeService;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @RequireRole({UserRole.LECTURER,UserRole.DEAN,UserRole.RECTOR})
    @PostMapping("/add")
    public ResponseEntity<GradeDto> createGrade(@RequestBody GradeChangeRequestDto dto) {
        GradeDto res = new GradeDto();
        Optional<GradeModel> model = gradeService.createOrUpdate(dto);
        if(model.isPresent()){
            res = model.get().MapToDto();
        }
        return ResponseEntity.ok(res);
    }

    @RequireRole({UserRole.STUDENT})
    @GetMapping("/student/{id}")
    public ResponseEntity<Collection<GradeDto>> getGradeByAssignedUserId(@PathVariable(name = "id")Long assignedUserId){
        List<GradeDto> responseDtos = new LinkedList<>();
        Optional<Collection<GradeModel>> models = gradeService.findGradesByAssignedUserId(assignedUserId);
        models.ifPresent( model -> model.forEach(m -> responseDtos.add(m.MapToDto())));
        return ResponseEntity.ok(responseDtos);
    }

    @RequireRole({UserRole.DEAN,UserRole.LECTURER,UserRole.RECTOR})
    @GetMapping("/lecturer/{id}")
    public ResponseEntity<Collection<GradeDto>> getGradeByLecturerUserId(@PathVariable(name = "id")Long lecturerUserId){
        List<GradeDto> responseDtos = new LinkedList<>();
        Optional<Collection<GradeModel>> models = gradeService.findGradesByCreatedByUserId(lecturerUserId);
        models.ifPresent( model -> model.forEach(m -> responseDtos.add(m.MapToDto())));
        return ResponseEntity.ok(responseDtos);
    }

    @CrossOrigin(methods = RequestMethod.DELETE)
    @RequireRole({UserRole.LECTURER})
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<StandardMessageResponse> deleteGrade(@PathVariable("id") Long id) {
        StandardMessageResponse response = new StandardMessageResponse();
        if(gradeService.delete(id))
            response.setMessage("Grade deleted.");
        else
            response.setMessage("Grade not found.");
        return ResponseEntity.ok(response);
    }
}
