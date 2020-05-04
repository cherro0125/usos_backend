package org.fibi.usos.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.user.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {
    @NotNull(message = "Please provide username.")
    private String username;
    @NotNull(message = "Please provide user password.")
    private String password;
    @NotNull(message = "Please provide user role.")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String firstName;
    private String lastName;
}
