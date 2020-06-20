package org.fibi.usos.controller.auth;

import org.fibi.usos.dto.Auth.AuthLoginResponseDto;
import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.dto.Auth.AuthRegisterResponseDto;
import org.fibi.usos.entity.facebook.FacebookLinkAccountRequest;
import org.fibi.usos.entity.facebook.FacebookLoginRequest;
import org.fibi.usos.entity.response.facebook.LinkInfoResponse;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

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
                res.setUser(usr.mapToDto());
                res.setMessage("User created.");
            });
        } catch (UserAlreadyExistsException e) {
            res.setMessage(e.getLocalizedMessage());
        } finally {
            return res;
        }
    }

    @PostMapping("/facebook/login")
    public ResponseEntity<AuthLoginResponseDto> loginByFacebook(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest){
        return ResponseEntity.of(authService.getLoginResponseForFacebookLogin(facebookLoginRequest));
    }

}
