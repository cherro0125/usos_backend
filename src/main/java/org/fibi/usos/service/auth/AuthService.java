package org.fibi.usos.service.auth;

import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.user.UserModel;

import java.util.Optional;

public interface AuthService {
    Optional<UserModel> createFromAuthRequest(AuthRegisterRequestDto req) throws UserAlreadyExistsException;
}
