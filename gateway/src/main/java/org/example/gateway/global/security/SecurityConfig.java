package org.example.gateway.global.security;

import lombok.RequiredArgsConstructor;
import org.example.gateway.global.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 보호 비활성화
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // 폼 로그인 비활성화
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/user/signup","/api/login","/api/logout").permitAll()
                        // signup URI는 권한 없이 허용
                        .pathMatchers("/api/user/**").hasRole("USER") // /user/** 경로는 ROLE_USER 권한 필요
                        .anyExchange().authenticated() // 나머지 요청은 인증 필요
                );
        http.addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }


}