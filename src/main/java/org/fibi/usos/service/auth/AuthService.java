package org.fibi.usos.service.auth;

import org.fibi.usos.dto.Auth.AuthLoginResponseDto;
import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.entity.facebook.FacebookLinkAccountRequest;
import org.fibi.usos.entity.facebook.FacebookLoginRequest;
import org.fibi.usos.entity.response.facebook.LinkInfoResponse;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.user.UserModel;

import java.util.Optional;

public interface AuthService {
    Optional<UserModel> createFromAuthRequest(AuthRegisterRequestDto req) throws UserAlreadyExistsException;
    Optional<AuthLoginResponseDto> getLoginResponseForFacebookLogin(FacebookLoginRequest req);
    Optional<LinkInfoResponse> linkFacebookToUser(FacebookLinkAccountRequest req);
    Optional<LinkInfoResponse> unlinkFacebookFromUser(Long userId);
}
