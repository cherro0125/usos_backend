package org.fibi.usos.repository.user;

import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {
    Optional<UserModel> findUserModelByUsername(String username);
    Optional<Collection<UserModel>> findAllByRole(UserRole role);
    Optional<UserModel> findUserModelByFacebookUserId(String facebookUserId);
}
