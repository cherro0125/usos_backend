package org.fibi.usos.controller.util;


import org.fibi.usos.dto.util.EnumUtilResponseDto;
import org.fibi.usos.exception.RegisteredEnumNotFoundException;
import org.fibi.usos.helper.RegisteredEnumHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping(value = "/util")
public class UtilController {
    @GetMapping(value = "/enum/{enumName}/values")
    public @ResponseBody
    ResponseEntity<EnumUtilResponseDto> getEnumValues(@PathVariable String enumName) {
        EnumUtilResponseDto dto = new EnumUtilResponseDto();
        try{
            Set<String> values = RegisteredEnumHelper.getAllFieldsName(enumName);
            dto.setEnumValues(values);
            dto.setMessage("Enum found.");
        } catch (RegisteredEnumNotFoundException e) {
            dto.setMessage("Enum not found.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);

    }
}
