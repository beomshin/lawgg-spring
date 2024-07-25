package com.kr.lg.config;

import com.kr.lg.security.filter.LoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SwaggerPatterns = { // swagger 페이지 인증 처리 미적용 패턴
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**"
    };

    private static final String LOGIN_PATH = "/api/public/login"; // 로그인 path

    private static final String LOGOUT_PATH = "/api/public/logout"; // 로그아웃 path (미사용 기능)

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider logAuthenticationProvider) { // security manager 등록
        List<AuthenticationProvider> authenticationProviders = Collections.singletonList(logAuthenticationProvider);
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            CorsConfigurationSource corsConfigurationSource,
            Filter jwtAuthenticationFilter,
            AuthenticationSuccessHandler loginSuccessHandler,
            AuthenticationFailureHandler loginFailHandler,
            LogoutSuccessHandler jwtLogoutSuccessHandler,
            LogoutHandler jwtLogoutHandler
    ) throws Exception {

        http.httpBasic().disable(); // REST API로 사용안함

        http.csrf().disable();

        http.formLogin().disable();

        http.headers().frameOptions().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // REST API 사용안함;

        http.cors().configurationSource(corsConfigurationSource);

        http.logout()
                .logoutUrl(LOGOUT_PATH)
                .addLogoutHandler(jwtLogoutHandler) // 로그아웃 핸들러
                .logoutSuccessHandler(jwtLogoutSuccessHandler) // 로그아웃 성공 핸들러
                .deleteCookies("refresh-token"); // 리프레쉬 토큰 쿠키 삭제

        http.authorizeHttpRequests()
                .antMatchers("/api/public/**").permitAll() // public path 허용
                .antMatchers(SwaggerPatterns).permitAll() // swagger path 허용
                .anyRequest().hasRole("USER"); // 이외 USER ROLE 확인 처리

        http.addFilter(new LoginAuthenticationFilter(authenticationManager, loginSuccessHandler, loginFailHandler, LOGIN_PATH)); // 로그인 필터 등록
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // before 필터 등록으로 JWT 검사 실행

        return http.build();
    }


}
