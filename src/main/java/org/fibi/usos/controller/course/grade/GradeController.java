package org.fibi.usos.controller.course.grade;

import org.fibi.usos.dto.Auth.AuthRegisterResponseDto;
import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.dto.grade.GradeDto;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.service.course.grade.GradeService;
import org.fibi.usos.service.course.grade.GradeStandardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

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
