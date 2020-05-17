package org.fibi.usos.service.user;

import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserStandardService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserStandardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserModel> createOrUpdate(UserModel newUser) {
        return Optional.of(userRepository.findUserModelByUsername(newUser.getUsername()).map(user -> {
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
        }));
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findUserModelByUsername(username);
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<Collection<UserModel>> findAllByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public Iterable<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserModelByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
