package org.fibi.usos.controller.course.grade;

import org.fibi.usos.dto.course.grade.GradeDto;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.service.course.grade.AddGradeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private AddGradeService addGradeService;

    public GradeController(AddGradeService addGradeService) {this.addGradeService = addGradeService;}

    @PostMapping("/addgrade")
    public GradeDto addGradeDto() {

        GradeDto gradeDto = new GradeDto();
        addGradeService.addNewGrade(new GradeModel());
        return gradeDto;
    }
}
