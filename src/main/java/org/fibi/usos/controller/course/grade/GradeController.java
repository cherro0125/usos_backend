package org.fibi.usos.controller.course.grade;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.dto.grade.GradeDto;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.course.grade.GradeService;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
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
}
