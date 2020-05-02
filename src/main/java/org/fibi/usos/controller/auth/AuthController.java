package org.fibi.usos.controller.auth;

import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.dto.Auth.AuthRegisterResponseDto;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.service.auth.AuthService;
import org.fibi.usos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthRegisterResponseDto register(@Valid @RequestBody AuthRegisterRequestDto authRequest) {
        AuthRegisterResponseDto res = new AuthRegisterResponseDto();
        try {
            authService.createFromAuthRequest(authRequest).ifPresent(usr -> {
                res.setUser(usr.MapToDto());
                res.setMessage("User created.");
            });
        } catch (UserAlreadyExistsException e) {
            res.setMessage(e.getLocalizedMessage());
        } finally {
            return res;
        }
    }
}
