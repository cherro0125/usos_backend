package org.fibi.usos.dto.Auth;

import org.fibi.usos.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponseDto {
    private UserDto user;
    private String token;
}
