package org.fibi.usos.repository.keys;

import org.fibi.usos.model.keys.KeyModel;
import org.fibi.usos.model.user.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KeyRepository extends CrudRepository<KeyModel, Long> {
    Optional<KeyModel> findKeyModelByRoomNumber(String roomNumber);
    Optional<Collection<KeyModel>> findByOwnerNotNull();
    Optional<Collection<KeyModel>> findByOwnerIsNull();
    Optional<Collection<KeyModel>> findByRoomNumberIn(List<String> roomNumbers);
    Optional<Collection<KeyModel>> findByOwner(UserModel userModel);
}
