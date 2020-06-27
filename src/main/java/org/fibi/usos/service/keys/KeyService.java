package org.fibi.usos.service.keys;

import org.fibi.usos.model.keys.KeyModel;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KeyService {
    Optional<KeyModel> createOrUpdate(KeyModel newKey);
    Optional<Collection<KeyModel>> getAllKeys();
    Optional<Collection<KeyModel>> getAllAvailableKeys();
    Optional<Collection<KeyModel>> getAllGivenKeys();
    Optional<Collection<KeyModel>> takeKeys(Long userWhichTookKeys, List<String> roomNumbers);
}
