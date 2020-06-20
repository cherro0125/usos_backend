package org.fibi.usos.service.auth;

import org.fibi.usos.dto.Auth.AuthLoginResponseDto;
import org.fibi.usos.dto.Auth.AuthRegisterRequestDto;
import org.fibi.usos.entity.facebook.FacebookLinkAccountRequest;
import org.fibi.usos.entity.facebook.FacebookLoginRequest;
import org.fibi.usos.entity.response.facebook.LinkInfoResponse;
import org.fibi.usos.entity.response.facebook.LinkInfoStatus;
import org.fibi.usos.exception.auth.UserAlreadyExistsException;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.user.UserService;
import org.fibi.usos.util.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthStandardService implements AuthService {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    Logger logger = LoggerFactory.getLogger(AuthStandardService.class);

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
        UserModel user = new UserModel(req.getUsername(),req.getFirstName(),req.getLastName(),passwordHash,role,null);
        return userService.createOrUpdate(user);
    }

    @Override
    public Optional<AuthLoginResponseDto> getLoginResponseForFacebookLogin(FacebookLoginRequest req) {
        Optional<UserModel> user = userService.findByFacebookUserId(req.getFacebookUserId());
        if(user.isPresent()){
            UserModel usr = user.get();
            AuthLoginResponseDto dto = new AuthLoginResponseDto();
            dto.setUser(usr.mapToDto());
            try {
                String token = JWTUtil.generateJWTToken(usr);
                dto.setToken(token);
                return Optional.of(dto);
            } catch (Exception e) {
                logger.trace(e.getLocalizedMessage(),e);
                return Optional.empty();
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<LinkInfoResponse> linkFacebookToUser(FacebookLinkAccountRequest req) {
        LinkInfoResponse linkInfoResponse = new LinkInfoResponse();
        LinkInfoStatus linkInfoStatus = userService.linkFacebookAccountToUser(req.getUserId(), req.getFacebookUserId());
        linkInfoResponse.setStatus(linkInfoStatus);
        if(linkInfoStatus != LinkInfoStatus.USER_NOT_EXISTS){
            userService.findById(req.getUserId()).map(UserModel::mapToDto).ifPresent(linkInfoResponse::setUser);
        }
        return Optional.of(linkInfoResponse);
    }

    @Override
    public Optional<LinkInfoResponse> unlinkFacebookFromUser(Long userId) {
        LinkInfoResponse linkInfoResponse = new LinkInfoResponse();
        LinkInfoStatus linkInfoStatus = userService.unlinkFacebook(userId);
        linkInfoResponse.setStatus(linkInfoStatus);
        if(linkInfoStatus != LinkInfoStatus.USER_NOT_EXISTS){
            userService.findById(userId).map(UserModel::mapToDto).ifPresent(linkInfoResponse::setUser);
        }
        return Optional.of(linkInfoResponse);
    }
}
