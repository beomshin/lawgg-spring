package com.kr.lg.security.login.detail;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
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
public class JwtDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // ToDo 캐시 적용 여부 확인
        log.debug("▶ [JwtDetailService] 토큰 유저 정보 조회 : userId [{}] =============>", userId);
        UserTb userTb = userRepository.findByUserId(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("로그인 아이디 미존재", new AuthException(AuthResultCode.NOT_EXIST_USER)));
        return new UserAdapter(userTb, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))); // userDetails 반환
    }
}
