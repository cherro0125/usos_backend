package org.fibi.usos.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.fibi.usos.constant.auth.SecurityConstants;
import org.fibi.usos.exception.JwtBadTokenToDecodeException;
import org.fibi.usos.exception.JwtNullUserException;
import org.fibi.usos.model.user.UserModel;

import java.util.Date;

import static org.fibi.usos.constant.auth.SecurityConstants.*;
import static org.fibi.usos.constant.auth.SecurityConstants.SECRET;

public class JWTUtil {
    public static String generateJWTToken(UserModel user) throws Exception {
        if(user == null)
            throw new JwtNullUserException();
        return  JWT.create()
                .withSubject(user.getUsername())
                .withClaim(USER_ROLE_CLAIM_NAME,user.getRole().toString())
                .withClaim(USER_ID_CLAIM_NAME,user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public static DecodedJWT decodedJWT(String token) throws JwtBadTokenToDecodeException {
        if(StringUtils.isBlank(token))
            throw new JwtBadTokenToDecodeException();
        return JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                .build()
                .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
    }
}
