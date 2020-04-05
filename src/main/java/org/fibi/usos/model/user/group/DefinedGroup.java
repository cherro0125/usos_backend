package org.fibi.usos.model.user.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DefinedGroup")
@Table(name = "defined_groups")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class DefinedGroup extends BaseIdentityModel {
    private String name;
    private String description;

    @OneToMany
    private Set<UserModel> students;
}
