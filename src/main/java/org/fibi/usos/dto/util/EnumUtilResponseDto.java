package org.fibi.usos.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumUtilResponseDto {
    private Iterable<String> enumValues;
    private String message;
}
