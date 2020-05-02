package org.fibi.usos.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.user.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
