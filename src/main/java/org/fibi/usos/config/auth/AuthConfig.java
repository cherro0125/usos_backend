package org.fibi.usos.config.auth;

import org.fibi.usos.constant.auth.SecurityConstants;
import org.fibi.usos.filter.auth.jwt.JWTAuthenticationFilter;
import org.fibi.usos.filter.auth.jwt.JWTAuthorizationFilter;
import org.fibi.usos.service.user.UserStandardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private UserStandardService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private List<String> ipAddressesForPayUCallback;

    public AuthConfig(UserStandardService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        ipAddressesForPayUCallback = Arrays.asList("185.68.14.10", "185.68.14.11", "185.68.14.12", "185.68.14.26", "185.68.14.27", "185.68.14.28");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(SecurityConstants.AUTH_PATTERN_URLS, SecurityConstants.UTIL_PATTERN_URLS)
                .permitAll()
                .antMatchers(SecurityConstants.PAYMENT_NOTIFY_PATTERN_URLS).access(createHasIpExpression())
                .anyRequest().authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private String createHasIpExpression(){
        return ipAddressesForPayUCallback.stream()
                .collect(Collectors.joining("') or hasIpAddress('", "hasIpAddress('","')"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
        return jwtAuthenticationFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
