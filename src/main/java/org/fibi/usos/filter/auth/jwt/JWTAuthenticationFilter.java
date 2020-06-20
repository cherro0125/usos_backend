package org.fibi.usos.filter.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.fibi.usos.dto.Auth.AuthLoginRequestDto;
import org.fibi.usos.dto.Auth.AuthLoginResponseDto;
import org.fibi.usos.exception.auth.BadAuthRequestException;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.strategy.exlcusion.JsonIgnoreExclusionStrategy;
import org.fibi.usos.util.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.fibi.usos.constant.auth.SecurityConstants.HEADER_STRING;
import static org.fibi.usos.constant.auth.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final static Gson gson;


    static{
        gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreExclusionStrategy()).create();
    }


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            AuthLoginRequestDto creds = new ObjectMapper().readValue(request.getInputStream(), AuthLoginRequestDto.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e){
            throw new BadAuthRequestException("Bad request.");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserModel user = (UserModel) authResult.getPrincipal();
        String token = null;
        try {
            token = JWTUtil.generateJWTToken(user);
        } catch (Exception e) {
            logger.error(e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        AuthLoginResponseDto dto = new AuthLoginResponseDto();
        dto.setUser(user.mapToDto());
        dto.setToken(token);
        out.print(gson.toJson(dto));
        out.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(failed instanceof BadAuthRequestException ? HttpStatus.BAD_REQUEST.value() : HttpStatus.UNAUTHORIZED.value());
    }
}
