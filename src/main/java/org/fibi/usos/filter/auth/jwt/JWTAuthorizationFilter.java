package org.fibi.usos.filter.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.fibi.usos.constant.auth.SecurityConstants;
import org.fibi.usos.exception.JwtBadTokenToDecodeException;
import org.fibi.usos.listener.seeder.course.degree.DegreeCourseSeeder;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.pool.RegisteredEnumPool;
import org.fibi.usos.service.auth.AuthService;
import org.fibi.usos.service.user.UserService;
import org.fibi.usos.util.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger  = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    @Autowired
    private UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<String> header = Optional.ofNullable(request.getHeader(SecurityConstants.HEADER_STRING));

        if (!header.isPresent() || !header.get().startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
            logger.error(e.getLocalizedMessage());
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) throws JwtBadTokenToDecodeException {
        Optional<String> token = Optional.ofNullable(request.getHeader(SecurityConstants.HEADER_STRING));

        if (token.isPresent()) {
            DecodedJWT decodedJWT = JWTUtil.decodedJWT(token.get());
            Optional<String> user = Optional.ofNullable(decodedJWT.getSubject());
            Optional<String> userRole = Optional.ofNullable(decodedJWT.getClaim(SecurityConstants.USER_ROLE_CLAIM_NAME).asString());

            if (user.isPresent() && userRole.isPresent()) {
                Optional<UserModel> userModel = userService.findByUsername(user.get());
                if(userModel.isPresent()){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.get());
                    return new UsernamePasswordAuthenticationToken(userModel.get(), null, Collections.singletonList(simpleGrantedAuthority));
                }
                return null;
            }
            return null;
        }
        return null;

    }
}
