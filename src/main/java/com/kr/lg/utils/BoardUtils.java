package com.kr.lg.utils;

import com.kr.lg.entities.UserTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.enums.entity.element.WriterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BoardUtils {

    private final BCryptPasswordEncoder encoder;

    public void isRightAnonymousPassword(CharSequence rawPassword, String encodedPassword, WriterEnum writerType) throws LgException {
        if (!encoder.matches(rawPassword, encodedPassword)) throw new LgException(GlobalCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
        else if (!writerType.equals(WriterEnum.ANONYMOUS_TYPE)) throw new LgException(GlobalCode.FAIL_ACCESS);
    }

    public void isRightUserPassword(UserTb loginUser, UserTb writerUser, CharSequence rawPassword, WriterEnum writerType) throws LgException {
        if (writerUser == null) throw new LgException(GlobalCode.NOT_EXIST_USER);
        else if (!loginUser.getUserId().equals(writerUser.getUserId())) throw new LgException(GlobalCode.FAIL_ACCESS); // 게시판 생성 유저가 아닌경우
        else if (!encoder.matches(rawPassword, loginUser.getPassword())) throw new LgException(GlobalCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
        else if (!writerType.equals(WriterEnum.MEMBER_TYPE)) throw new LgException(GlobalCode.FAIL_ACCESS);
    }

    public void isRightUserNonePassword(UserTb loginUser, UserTb writerUser, WriterEnum writerType) throws LgException {
        if (writerUser == null) throw new LgException(GlobalCode.NOT_EXIST_USER);
        else if (!loginUser.getUserId().equals(writerUser.getUserId())) throw new LgException(GlobalCode.FAIL_ACCESS); // 게시판 생성 유저가 아닌경우
        else if (!writerType.equals(WriterEnum.MEMBER_TYPE)) throw new LgException(GlobalCode.FAIL_ACCESS);
    }

    public void isRightCommentAnonymousPassword(CharSequence rawPassword, String encodedPassword) throws LgException {
        if (!encoder.matches(rawPassword, encodedPassword)) throw new LgException(GlobalCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
    }

    public void isRightCommentUserPassword(UserTb loginUser, UserTb writerUser, CharSequence rawPassword) throws LgException {
        if (writerUser == null) throw new LgException(GlobalCode.NOT_EXIST_USER);
        else if (!loginUser.getUserId().equals(writerUser.getUserId())) throw new LgException(GlobalCode.FAIL_ACCESS); // 게시판 생성 유저가 아닌경우
        else if (!encoder.matches(rawPassword, loginUser.getPassword())) throw new LgException(GlobalCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
    }

    public void isRightCommentUserNonePassword(UserTb loginUser, UserTb writerUser) throws LgException {
        if (writerUser == null) throw new LgException(GlobalCode.NOT_EXIST_USER);
        else if (!loginUser.getUserId().equals(writerUser.getUserId())) throw new LgException(GlobalCode.FAIL_ACCESS); // 댓글 생성 유저가 아닌경우
    }
}
