package org.fibi.usos.model.user;

import org.fibi.usos.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "User")
@Table(name = "users")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class UserModel extends BaseIdentityModel implements UserDetails {
    @Column(unique = true,nullable = false)
    private String username;
    private String firstName;
    private String lastName;
    @JsonIgnore
    @Column(nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto mapToDto(){
        UserDto dto = new UserDto();
        dto.setId(getId());
        dto.setRole(getRole());
        dto.setUsername(getUsername());
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        return dto;
    }
}
