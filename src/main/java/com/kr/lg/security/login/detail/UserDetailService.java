package com.kr.lg.security.login.detail;

import com.kr.lg.common.enums.entity.status.UserStatus;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.db.repositories.RootUserRepository;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
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

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserDetailService implements  UserDetailsService {

    private final RootUserRepository userRepository;

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

        log.info("▶ [Spring Security 로그인][UserDetailService] 3. 유저 조회 및 상태 체크: loginId - [{}]", loginId);
        UserTb userTb = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("로그인 아이디 미존재", new SecurityException(AuthResultCode.NOT_EXIST_USER)));

        // 아이디 상태 검사 (정지, 삭제)
        if (userTb.getStatus() == UserStatus.REPORT) {
            log.error("▶ [Spring Security 로그인][UserDetailService] 아이디 정지 상태");
            throw new UsernameNotFoundException("정지 아이디", new SecurityException(AuthResultCode.LOCK_LOGIN_ID));
        } else if (userTb.getStatus() == UserStatus.DELETE) {
            log.error("▶ [Spring Security 로그인][UserDetailService] 아이디 삭제 상태");
            throw new UsernameNotFoundException("삭제 아이디", new SecurityException(AuthResultCode.DELETE_LOGIN_ID));
        }

        return new UserAdapter(userTb, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))); // userDetails 반환
    }

}
