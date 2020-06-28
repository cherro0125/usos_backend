package org.fibi.usos.controller.keys;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.keys.GiveKeysRequestDto;
import org.fibi.usos.dto.keys.KeyResponseDto;
import org.fibi.usos.dto.keys.ReturnKeysRequestDto;
import org.fibi.usos.model.keys.KeyModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.keys.KeyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/keys")
public class KeysController {

    private KeyService keyService;

    public KeysController(KeyService keyService) {
        this.keyService = keyService;
    }

    @RequireRole({UserRole.PORTER})
    @GetMapping("/all")
    public ResponseEntity<List<KeyResponseDto>> getAll() {
        List<KeyResponseDto> keys = new ArrayList<>();
        Optional<Collection<KeyModel>> models = keyService.getAllKeys();
        models.ifPresent(keyModels -> keyModels.forEach(keyModel -> keys.add(keyModel.mapToResponseDto())));
        return ResponseEntity.ok(keys);
    }

    @RequireRole({UserRole.PORTER})
    @GetMapping("/allAvailableKeys")
    public ResponseEntity<List<KeyResponseDto>> getAllAvailableKeys() {
        List<KeyResponseDto> keys = new ArrayList<>();
        Optional<Collection<KeyModel>> models = keyService.getAllAvailableKeys();
        models.ifPresent(keyModels -> keyModels.forEach(keyModel -> keys.add(keyModel.mapToResponseDto())));
        return ResponseEntity.ok(keys);
    }

    @RequireRole({UserRole.PORTER})
    @GetMapping("/allGivenKeys")
    public ResponseEntity<List<KeyResponseDto>> getAllGivenKeys() {
        List<KeyResponseDto> keys = new ArrayList<>();
        Optional<Collection<KeyModel>> models = keyService.getAllGivenKeys();
        models.ifPresent(keyModels -> keyModels.forEach(keyModel -> keys.add(keyModel.mapToResponseDto())));
        return ResponseEntity.ok(keys);
    }

    @RequireRole({UserRole.PORTER})
    @PostMapping(value = "/giveKeys", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeyResponseDto>> giveKeys(@RequestBody GiveKeysRequestDto dto) throws Exception {
        List<KeyResponseDto> keys = new ArrayList<>();
        Optional<Collection<KeyModel>> models = keyService.giveKeys(dto.getUserId(), dto.getRoomNumbers());
        models.ifPresent(keyModels -> keyModels.forEach(keyModel -> keys.add(keyModel.mapToResponseDto())));
        return ResponseEntity.ok(keys);
    }

    @RequireRole({UserRole.PORTER})
    @PostMapping(value = "/returnKeys", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeyResponseDto>> returnKeys(@RequestBody ReturnKeysRequestDto dto) throws Exception {
        List<KeyResponseDto> keys = new ArrayList<>();
        Optional<Collection<KeyModel>> models = keyService.returnKeys(dto.getRoomNumbers());
        models.ifPresent(keyModels -> keyModels.forEach(keyModel -> keys.add(keyModel.mapToResponseDto())));
        return ResponseEntity.ok(keys);
    }
}
