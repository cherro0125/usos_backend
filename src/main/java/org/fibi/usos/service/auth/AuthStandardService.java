package org.fibi.usos.service.auth;

import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthStandardService implements AuthService {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthStandardService(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<UserModel> createFromAuthRequest(AuthRegisterRequestDto req) throws UserAlreadyExistsException {
        Optional<UserModel> model = userService.findByUsername(req.getUsername());
        if(model.isPresent())
            throw new UserAlreadyExistsException();
        String passwordHash = bCryptPasswordEncoder.encode(req.getPassword());
        UserRole role = req.getRole() != null ? req.getRole() : UserRole.STUDENT;
        UserModel user = new UserModel(req.getUsername(),passwordHash,role);
        return userService.createOrUpdate(user);
    }
}
