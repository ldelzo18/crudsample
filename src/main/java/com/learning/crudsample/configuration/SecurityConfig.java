package com.learning.crudsample.configuration;

import com.learning.crudsample.infrastructure.security.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swaggerdoc.html").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
