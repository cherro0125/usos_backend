package org.fibi.usos.dto.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnKeysRequestDto {
    private List<String> roomNumbers;
}
