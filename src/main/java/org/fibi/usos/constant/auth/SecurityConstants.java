package org.fibi.usos.constant.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    @Value("${usos.app.jwtSecret}")
    private String secret;
    @Value("${usos.app.expirationTime}")
    private long ExpirationTime;
    public static String SECRET;
    public static long EXPIRATION_TIME;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_PATTERN_URLS = "/auth/**";
    public static final String UTIL_PATTERN_URLS = "/util/**";
    public static final String AUTH_LOGIN_URL = "/auth/login";
    public static final String USER_ID_CLAIM_NAME = "USER_ID";
    public static final String USER_ROLE_CLAIM_NAME = "USER_ROLE";

    @Value("${usos.app.jwtSecret}")
    public void setSecretStatic(String secret){
        SecurityConstants.SECRET = secret;
    }
    @Value("${usos.app.expirationTime}")
    public void setExpirationTimeStatic(long expirationTime){
        SecurityConstants.EXPIRATION_TIME = expirationTime;
    }
}
