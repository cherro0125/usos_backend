package org.fibi.usos.service.user;

import org.fibi.usos.model.user.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserModel> createOrUpdate(UserModel user);
    Optional<UserModel> findByUsername(String username);
}
