package org.fibi.usos.listener.seeder.keys;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.keys.KeyModel;
import org.fibi.usos.service.keys.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class KeySeeder implements Seeder {

    private static final Logger logger  = LoggerFactory.getLogger(KeySeeder.class);

    private KeyService keyService;

    private List<KeyModel> keys = new ArrayList<>();

    public KeySeeder(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    public void init() {
        KeyModel keyModel1 = new KeyModel();
        keyModel1.setRoomNumber("3.12D");
        keyModel1.setOwner(null);
        keys.add(keyModel1);

        KeyModel keyModel2 = new KeyModel();
        keyModel2.setRoomNumber("3.19D");
        keyModel2.setOwner(null);
        keys.add(keyModel2);

        KeyModel keyModel3 = new KeyModel();
        keyModel3.setRoomNumber("3.26D");
        keyModel3.setOwner(null);
        keys.add(keyModel3);

        KeyModel keyModel4 = new KeyModel();
        keyModel4.setRoomNumber("3.06D");
        keyModel4.setOwner(null);
        keys.add(keyModel4);
    }

    @Override
    public void seed() {
        init();
        keys.forEach(this::createOrUpdateWrapper);
    }

    public void createOrUpdateWrapper(KeyModel keyModel) {
        Optional<KeyModel> optionalKeyModel = keyService.createOrUpdate(keyModel);
        optionalKeyModel.ifPresent(createdKeyModel -> logger.info("Create key " + createdKeyModel));
    }
}
