package org.fibi.usos.service.course.grade;

import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.repository.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AddGradeService implements GradeService{

    private GradeRepository gradeRepository;

    @Autowired
    public AddGradeService(GradeRepository gradeRepository) {this.gradeRepository = gradeRepository;}

    @Override
    public Optional<GradeModel> addNewGrade(GradeModel grade) {

    }
}
