package org.fibi.usos.dto.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.user.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyResponseDto {
    private String roomNumber;
    private UserDto owner;
}
