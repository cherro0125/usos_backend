package org.fibi.usos.listener.seeder;

import java.util.Optional;
import java.util.logging.Logger;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder implements Seeder {

    private static final Logger logger  = Logger.getLogger(UserSeeder.class.getName());
    private List<UserModel> users = new ArrayList<>();
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserSeeder(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init() {
        //STUDENT
        UserModel student = new UserModel("student",passwordEncoder.encode("student"), UserRole.STUDENT);
        users.add(student);
        //RECTOR
        UserModel rector = new UserModel("rector",passwordEncoder.encode("rector"), UserRole.RECTOR);
        users.add(rector);
        //LECTURER
        UserModel lecturer = new UserModel("lecturer",passwordEncoder.encode("lecturer"), UserRole.LECTURER);
        users.add(lecturer);
        //DEAN
        UserModel dean = new UserModel("dean",passwordEncoder.encode("dean"), UserRole.DEAN);
        users.add(dean);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void seed() {
        init();
        users.forEach(this::createOrUpdateWrapper);
    }

    public void createOrUpdateWrapper(UserModel user){
        Optional<UserModel> createdUser = userService.createOrUpdate(user);
        createdUser.ifPresent(userModel -> logger.info("Create user " + userModel));
    }
}
