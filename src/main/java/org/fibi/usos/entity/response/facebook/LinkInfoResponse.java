package org.fibi.usos.entity.response.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.user.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkInfoResponse {
    private LinkInfoStatus status;
    private UserDto user;
}
