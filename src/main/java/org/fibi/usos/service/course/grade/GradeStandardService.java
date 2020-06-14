package org.fibi.usos.service.course.grade;

import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.model.course.grade.GradeModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.course.grade.GradeRepository;
import org.fibi.usos.repository.course.group.CourseGroupRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GradeStandardService implements GradeService{

    private GradeRepository gradeRepository;
    private UserRepository userRepository;
    private CourseGroupRepository courseGroupRepository;

    public GradeStandardService(GradeRepository gradeRepository, UserRepository userRepository, CourseGroupRepository courseGroupRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.courseGroupRepository = courseGroupRepository;
    }

    @Override
    public Optional<GradeModel> createOrUpdate(GradeChangeRequestDto grade) {
        return Optional.of(gradeRepository.findGradeModelByCourseGroupIdAndAssignedUserIdAndExamDateType(grade.getCourseGroupId(),grade.getAssignedUserId(),grade.getExamDateType()).map(g -> {
            Optional<UserModel> assignedUser = userRepository.findById(grade.getAssignedUserId());
            assignedUser.ifPresent(g::setAssignedUser);
            userRepository.findById(grade.getCreatedById()).ifPresent(g::setCreatedBy);
            g.setDescription(grade.getDescription());
            g.setExamDateType(grade.getExamDateType());
            g.setValue(grade.getValue());
            courseGroupRepository.findById(grade.getCourseGroupId()).ifPresent(g::setCourseGroup);
            return gradeRepository.save(g);
        }).orElseGet(() ->{
            GradeModel g = new GradeModel();
            Optional<UserModel> assignedUser = userRepository.findById(grade.getAssignedUserId());
            assignedUser.ifPresent(g::setAssignedUser);
            userRepository.findById(grade.getCreatedById()).ifPresent(g::setCreatedBy);
            g.setDescription(grade.getDescription());
            g.setExamDateType(grade.getExamDateType());
            g.setValue(grade.getValue());
            courseGroupRepository.findById(grade.getCourseGroupId()).ifPresent(g::setCourseGroup);
            return gradeRepository.save(g);
        }));
    }



    @Override
    public Optional<GradeModel> findGradeById(Long id) {
        return gradeRepository.findGradeModelById(id);
    }

    @Override
    public Optional<Collection<GradeModel>> findGradesByAssignedUserId(Long assignedUserId) {
        return gradeRepository.findAllByAssignedUserId(assignedUserId);
    }

    @Override
    public Optional<Collection<GradeModel>> findGradesByCreatedByUserId(Long createdByUserId) {
        return gradeRepository.findAllByCreatedById(createdByUserId);
    }

    public boolean delete(Long id) {
        if(gradeRepository.findById(id).isPresent()){
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
