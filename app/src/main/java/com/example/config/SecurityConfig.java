package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Swagger UI 및 관련 엔드포인트 허용
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // 회원가입 API 허용
                .requestMatchers("/api/users/register").permitAll()
                // 나머지 요청은 인증 요구
                .anyRequest().authenticated()
            )
            // 기본 폼 로그인 활성화 (필요 시)
            .formLogin(form -> form.permitAll())
            // HTTP Basic 인증 활성화 (선택)
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}