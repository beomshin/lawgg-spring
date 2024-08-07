package com.kr.lg.security.provider;

import com.kr.lg.security.login.detail.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder encoder;
    private final UserDetailService userDetailService;

    /**
     * Spring Security Manager 가 실제로 실행하는 로그인 검증 구현체
     *
     * 성공시 성공 핸들러 (AuthenticationSuccessHandler)
     * Exception 발생으로 실패 핸들러 (AuthenticationSuccessHandler)
     *
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("▶ [Spring Security 로그인][LoginAuthenticationProvider] 2. 로그인 Provider 진입");
        UserDetails userDetails = userDetailService.loadUserByUsername(authentication.getName());

        log.info("▶ [Spring Security 로그인][LoginAuthenticationProvider] 4. 패스워드 검증 시작");
        String rawPassword = authentication.getCredentials().toString(); // 요청 비밀번호
        String oriPassword = userDetails.getPassword(); // 원본 비밀번호
        if (!encoder.matches(rawPassword, oriPassword)) {
            throw new BadCredentialsException("비밀번호 불일치");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
