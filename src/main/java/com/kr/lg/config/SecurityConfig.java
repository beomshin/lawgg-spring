package com.kr.lg.config;


import com.kr.lg.security.login.detail.RememberDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // @Secured 활성화, @PreAuthorize & @PostAuthorize 활성화
public class SecurityConfig {

    private static final String[] SwaggerPatterns = { // swagger 페이지 인증 처리 미적용 패턴
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**"
    };

    private static final String[] Static = {
            "/static/**",
            "/css/**",
            "/js/**",
            "/fonts/**"
    };

    private static final int DAY_7 = 604800; // 7일

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider logAuthenticationProvider) { // security manager 등록
        List<AuthenticationProvider> authenticationProviders = Collections.singletonList(logAuthenticationProvider);
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web
                .ignoring()
                .antMatchers(Static)
                .antMatchers(SwaggerPatterns);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CorsConfigurationSource corsConfigurationSource,
            AuthenticationSuccessHandler loginSuccessHandler,
            AuthenticationFailureHandler loginFailHandler,
            RememberDetailService userDetailService
    ) throws Exception {

        http.csrf((csrf) -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()));

        http.cors().configurationSource(corsConfigurationSource);

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .maximumSessions(1);

        http.formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login/process")
                        .usernameParameter("loginId")
                        .successHandler(loginSuccessHandler)
                        .failureHandler(loginFailHandler)
                        .permitAll();

        http.rememberMe()
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(DAY_7)
                        .userDetailsService(userDetailService)
                        .authenticationSuccessHandler(loginSuccessHandler);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true) // 세션 무효화
                .logoutSuccessUrl("/") // 로그아웃 성공 핸들러
                .deleteCookies("JSESSIONID", "remember-me");     // 로그아웃 후 쿠키 삭제

        String[] paths = {"/my/info", "/my/alerts", "/my/messages", "/my/boards", "/trial/write", "/law-firm/write"};

        http.authorizeHttpRequests()
                .antMatchers(paths).hasRole("USER")
                .anyRequest().permitAll(); // 이외 USER ROLE 확인 처리

        return http.build();
    }


}
