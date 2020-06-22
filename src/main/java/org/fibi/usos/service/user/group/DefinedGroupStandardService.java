package org.fibi.usos.service.user.group;

import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
import org.fibi.usos.dto.user.group.DefinedGroupStudentsRequestDto;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.group.DefinedGroup;
import org.fibi.usos.repository.course.degree.DegreeCourseRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.fibi.usos.repository.user.group.DefinedGroupRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefinedGroupStandardService implements DefinedGroupService{
    private DefinedGroupRepository definedGroupRepository;
    private DegreeCourseRepository degreeCourseRepository;
    private UserRepository userRepository;

    public DefinedGroupStandardService(DefinedGroupRepository definedGroupRepository, DegreeCourseRepository degreeCourseRepository, UserRepository userRepository) {
        this.definedGroupRepository = definedGroupRepository;
        this.degreeCourseRepository = degreeCourseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<DefinedGroup> findByName(String name) {
        return definedGroupRepository.findByName(name);
    }

    @Override
    public Optional<Collection<DefinedGroup>> getAllByDegreeCourseId(Long degreeCourseId) {
        return definedGroupRepository.getAllByDegreeCourseId(degreeCourseId);
    }

    @Override
    public Optional<DefinedGroup> create(DefinedGroupRequestDto dto) {
        DefinedGroup d = new DefinedGroup();
        d.setName(dto.getName());
        d.setDescription(dto.getDescription());
        degreeCourseRepository.findById(dto.getDegreeCourseId()).ifPresent(d::setDegreeCourse);
        return Optional.of(definedGroupRepository.save(d));
    }

    @Override
    public Optional<Iterable<DefinedGroup>> getAll() {
        Iterable<DefinedGroup> models = definedGroupRepository.findAll();
        return models.iterator().hasNext() ? Optional.of(models) : Optional.empty();
    }

    @Override
    public Optional<DefinedGroup> findById(Long id) {
        return definedGroupRepository.findById(id);
    }

    @Override
    public Optional<DefinedGroup> addStudent(DefinedGroupStudentsRequestDto dto) {
        Optional<DefinedGroup> model = definedGroupRepository.findById(dto.getDefinedGroupId());
        if(model.isPresent()){
            Iterable<UserModel> students = userRepository.findAllById(Arrays.asList(dto.getStudentIds()));
            Set<UserModel> studentsSet = model.get().getStudents();
            for (UserModel student : students) {
                studentsSet.add(student);
            }
            model.get().setStudents(studentsSet);
            return Optional.of(definedGroupRepository.save(model.get()));
        }
        return  Optional.empty();

    }
    @Override
    public boolean delete(Long id) {
        if(definedGroupRepository.findById(id).isPresent()){
            definedGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeStudent(Long id, Long studentId) {
        if(definedGroupRepository.findById(id).isPresent()){
            if(userRepository.findById(studentId).isPresent()) {
                DefinedGroup group = definedGroupRepository.findById(id).get();

                UserModel studentToRemove = userRepository.findById(studentId).get();
                group.getStudents().remove(studentToRemove);
                definedGroupRepository.save(group);
                return true;
            }
        }
        return false;
    }
}
