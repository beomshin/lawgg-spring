package com.kr.lg.security.login.detail;

import com.kr.lg.common.enums.entity.flag.JudgeUserFlag;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.security.exception.SecurityResultCode;
import com.kr.lg.security.exception.SecurityException;
import com.kr.lg.db.entities.UserTb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserDetailService implements  UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 아이디 로그인 체크
     *
     * 로그인 실패시 UsernameNotFoundException 발생으로 AuthenticationFailureHandler(LoginFailHandler) 핸들러 실행
     *
     * @param loginId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        log.info("▶ [Spring Security 로그인][UserDetailService] 2. 유저 조회 및 상태 체크: loginId - [{}]", loginId);
        UserTb userTb = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("로그인 아이디 미존재", new SecurityException(SecurityResultCode.NOT_EXIST_USER)));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        if (userTb.getJudgeFlag() == JudgeUserFlag.USE_STATUS) { // 재판 가능 권한 부여
            authorities.add(new SimpleGrantedAuthority("ROLE_JUDGE"));
        }

        return new UserAdapter(userTb, authorities); // userDetails 반환
    }

}
