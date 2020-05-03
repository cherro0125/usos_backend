package org.fibi.usos.service.course.grade;

import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.repository.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddGradeService extends BaseIdentityModel implements GradeService{

    private GradeRepository gradeRepository;

    @Autowired
    public AddGradeService(GradeRepository gradeRepository) {this.gradeRepository = gradeRepository;}

    @Override
    public Optional<GradeModel> addNewGrade(GradeModel newGrade) {
        GradeModel grade = new GradeModel();
        grade.setDescription(newGrade.getDescription());
        grade.setValue(newGrade.getValue());
        grade.setExamDateType(newGrade.getExamDateType());
        grade.setCourseGroup(newGrade.getCourseGroup());
        grade.setAssignedUser(newGrade.getAssignedUser());
        grade.setCreatedBy(newGrade.getCreatedBy());
        return Optional.of(gradeRepository.save(grade));

    }

    @Override
    public Optional<GradeModel> findStudentById(Long id) {
        return gradeRepository.findGradeModelById(id);
    }
}
