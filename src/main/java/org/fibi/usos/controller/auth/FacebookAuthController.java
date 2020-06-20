package org.fibi.usos.controller.auth;

import org.fibi.usos.entity.facebook.FacebookLinkAccountRequest;
import org.fibi.usos.entity.response.facebook.LinkInfoResponse;
import org.fibi.usos.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/facebook/auth")
public class FacebookAuthController {

    private AuthService authService;

    public FacebookAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/link")
    public ResponseEntity<LinkInfoResponse> linkFacebookAccount(@Valid @RequestBody FacebookLinkAccountRequest facebookLinkAccountRequest){
        return ResponseEntity.of(authService.linkFacebookToUser(facebookLinkAccountRequest));
    }

    @GetMapping("/unlink/{userId}")
    public ResponseEntity<LinkInfoResponse> linkFacebookAccount(@PathVariable("userId")Long userId){
        return ResponseEntity.of(authService.unlinkFacebookFromUser(userId));
    }
}
