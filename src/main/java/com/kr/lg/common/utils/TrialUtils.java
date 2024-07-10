package com.kr.lg.common.utils;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.global.GlobalCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrialUtils {
    private final BCryptPasswordEncoder encoder;

    public void isWriterUser(UserTb userTb1, UserTb userTb2) throws LgException {
        if (userTb1.getUserId() != userTb2.getUserId()) throw new LgException(GlobalCode.FAIL_ACCESS);
    }

    public void isRightUserPassword(UserTb loginUser, UserTb writerUser , CharSequence rawPassword) throws LgException {
        if (loginUser.getUserId() != writerUser.getUserId()) throw new LgException(GlobalCode.FAIL_ACCESS);
        else if (!encoder.matches(rawPassword, loginUser.getPassword())) throw new LgException(GlobalCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
    }

}
