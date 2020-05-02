package org.fibi.usos.listener;

import org.fibi.usos.model.scholarship.ScholarshipRequestDocumentModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.repository.scholarship.request.ScholarshipRequestDocumentRepository;
import org.fibi.usos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TestListener {

    private ScholarshipRequestDocumentRepository scholarshipRequestDocumentRepository;
    private UserService userService;

    @Autowired
    public TestListener(ScholarshipRequestDocumentRepository scholarshipRequestDocumentRepository, UserService userService) {
        this.scholarshipRequestDocumentRepository = scholarshipRequestDocumentRepository;
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test(){

        UserModel user1 = new UserModel();
        user1.setPasswordHash("eee");
        user1.setUsername("user1");
        user1.setRole(UserRole.RECTOR);
        Optional<UserModel> usr1 = userService.createOrUpdate(user1);

        UserModel user2 = new UserModel();
        user2.setPasswordHash("bbb");
        user2.setUsername("user1");
        user2.setRole(UserRole.STUDENT);
        Optional<UserModel> usr2 = userService.createOrUpdate(user2);
        if(usr1.isPresent() && usr2.isPresent()){
            ScholarshipRequestDocumentModel requestModel = new ScholarshipRequestDocumentModel();
            requestModel.setApprover(usr1.get());
            requestModel.setApplicant(usr2.get());
            scholarshipRequestDocumentRepository.save(requestModel);
        }

    }
}
