package org.fibi.usos.dto.Auth;

import dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterResponseDto {
    private UserDto user;
    private String message;
}
