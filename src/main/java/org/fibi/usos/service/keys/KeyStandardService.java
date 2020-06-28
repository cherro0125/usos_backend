package org.fibi.usos.service.keys;

import org.fibi.usos.model.keys.KeyModel;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.keys.KeyRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class KeyStandardService implements KeyService {

    private KeyRepository keyRepository;
    private UserRepository userRepository;

    public KeyStandardService(KeyRepository keyRepository, UserRepository userRepository) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<KeyModel> createOrUpdate(KeyModel newKey) {
        return Optional.of(keyRepository.findKeyModelByRoomNumber(newKey.getRoomNumber()).map(keyModel -> {
            keyModel.setRoomNumber(newKey.getRoomNumber());
            keyModel.setOwner(newKey.getOwner());
            return keyRepository.save(keyModel);
        }).orElseGet(() ->{
            KeyModel keyModel = new KeyModel();
            keyModel.setRoomNumber(newKey.getRoomNumber());
            keyModel.setOwner(newKey.getOwner());
            return keyRepository.save(keyModel);
        }));
    }

    @Override
    public Optional<Collection<KeyModel>> getAllKeys() {
        List<KeyModel> list = new ArrayList<>();
        keyRepository.findAll()
                .forEach(list::add);
        return Optional.of(list);
    }

    @Override
    public Optional<Collection<KeyModel>> getAllAvailableKeys() {
        return keyRepository.findByOwnerIsNull();
    }

    @Override
    public Optional<Collection<KeyModel>> getAllGivenKeys() {
        return keyRepository.findByOwnerNotNull();
    }

    @Override
    @Transactional
    public Optional<Collection<KeyModel>> giveKeys(Long userWhichTookKeys, List<String> roomNumbers) throws Exception {
        Optional<UserModel> optionalUser = userRepository.findById(userWhichTookKeys);
        Optional<Collection<KeyModel>> optionalKeyModels = keyRepository.findByRoomNumberIn(roomNumbers);

        if (optionalUser.isPresent() && optionalKeyModels.isPresent()) {
            UserModel user = optionalUser.get();
            Collection<KeyModel> keyModels = optionalKeyModels.get();

            for (KeyModel keyModel : keyModels) {
                if (keyModel.getOwner() == null) {
                    keyModel.setOwner(user);
                } else {
                    throw new Exception("Key for room " + keyModel.getRoomNumber() + " was already taken!");
                }
            }
        }

        if (optionalUser.isPresent()) {
            return keyRepository.findByOwner(optionalUser.get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Collection<KeyModel>> returnKeys(List<String> roomNumbers) throws Exception {
        Optional<Collection<KeyModel>> optionalKeyModels = keyRepository.findByRoomNumberIn(roomNumbers);

        if (optionalKeyModels.isPresent()) {
            Collection<KeyModel> keyModels = optionalKeyModels.get();

            for (KeyModel keyModel : keyModels) {
                if (keyModel.getOwner() != null) {
                    keyModel.setOwner(null);
                } else {
                    throw new Exception("Key for room " + keyModel.getRoomNumber() + " wasn't taken!");
                }
            }
        }

        return getAllAvailableKeys();
    }


}
