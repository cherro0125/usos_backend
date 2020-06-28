package org.fibi.usos.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.keys.KeyResponseDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Key")
@Table(name = "keys_table") // table "keys" is not allowed in MariaDB
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class KeyModel extends BaseIdentityModel {

    @Column(unique = true)
    private String roomNumber;
    @OneToOne
    private UserModel owner;

    public KeyResponseDto mapToResponseDto() {
        KeyResponseDto keyResponseDto = new KeyResponseDto();
        keyResponseDto.setRoomNumber(getRoomNumber());
        keyResponseDto.setOwner(getOwner() != null ? getOwner().mapToDto() : null);
        return keyResponseDto;
    }
}
