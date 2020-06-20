package org.fibi.usos.listener.seeder.user;

import java.util.Optional;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.pool.RegisteredEnumPool;
import org.fibi.usos.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder implements Seeder {

    private static final Logger logger  = LoggerFactory.getLogger(UserSeeder.class);
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
        UserModel student = new UserModel("student","Jan","Kowalski",passwordEncoder.encode("student"), UserRole.STUDENT,null);
        users.add(student);
        //STUDENT
        UserModel student2 = new UserModel("student2","Zenon","Pijak",passwordEncoder.encode("student2"), UserRole.STUDENT,null);
        users.add(student2);
        //RECTOR
        UserModel rector = new UserModel("rector","Wiesław","Monciwoda",passwordEncoder.encode("rector"), UserRole.RECTOR,null);
        users.add(rector);
        //LECTURER
        UserModel lecturer = new UserModel("lecturer","Tomasz","Neural",passwordEncoder.encode("lecturer"), UserRole.LECTURER,null);
        users.add(lecturer);
        //DEAN
        UserModel dean = new UserModel("dean","Janusz","Dziekan",passwordEncoder.encode("dean"), UserRole.DEAN,null);
        users.add(dean);
    }


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
