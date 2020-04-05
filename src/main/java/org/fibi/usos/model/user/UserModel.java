package org.fibi.usos.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.fibi.usos.model.base.BaseIdentityModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "User")
@Table(name = "users")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class UserModel extends BaseIdentityModel {
    @Column(unique = true,nullable = false)
    private String username;
    @JsonIgnore
    @Column(nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
