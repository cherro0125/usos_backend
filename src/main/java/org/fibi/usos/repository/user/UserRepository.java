package org.fibi.usos.repository.user;

import org.fibi.usos.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {
    Optional<UserModel> findUserModelByUsername(String username);
}
