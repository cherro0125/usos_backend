package org.fibi.usos.service.user;

import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStandardService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserStandardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel createOrUpdate(UserModel newUser) {
        return userRepository.findUserModelByUsername(newUser.getUsername()).map(user -> {
            user.setPasswordHash(newUser.getPasswordHash());
            user.setUsername(newUser.getUsername());
            user.setRole(newUser.getRole());
            return userRepository.save(user);
        }).orElseGet(() ->{
            UserModel user = new UserModel();
            user.setPasswordHash(newUser.getPasswordHash());
            user.setUsername(newUser.getUsername());
            user.setRole(newUser.getRole());
            return userRepository.save(newUser);
        });
    }
}
