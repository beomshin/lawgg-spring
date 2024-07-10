package com.kr.lg.config;

import com.kr.lg.web.filters.security.LoginAuthenticationFilter;
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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider logAuthenticationProvider) {
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
                .logoutUrl("/api/public/logout")
                .addLogoutHandler(jwtLogoutHandler)
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                .deleteCookies("refresh-token");

        http.authorizeHttpRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers(SwaggerPatterns).permitAll()
                .anyRequest().hasRole("USER");

        http.addFilter(new LoginAuthenticationFilter(authenticationManager, loginSuccessHandler, loginFailHandler, "/api/public/login"));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}
