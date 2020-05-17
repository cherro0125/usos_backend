package org.fibi.usos.service.user;

import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserModel> createOrUpdate(UserModel user);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findById(Long id);
    Optional<Collection<UserModel>> findAllByRole(UserRole role);
    Iterable<UserModel> findAll();
    boolean remove(Long id);
}
