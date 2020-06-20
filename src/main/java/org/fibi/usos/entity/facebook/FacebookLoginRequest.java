package org.fibi.usos.entity.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacebookLoginRequest {
    @NotNull
    private String facebookUserId;
    private String accessToken;
}
