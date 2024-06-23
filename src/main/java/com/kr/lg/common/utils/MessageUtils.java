package com.kr.lg.common.utils;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.global.GlobalCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageUtils {


    public void checkSelfSend(UserTb userTb1, UserTb userTb2) throws LgException {
        if (userTb1.getUserId() == userTb2.getUserId()) throw new LgException(GlobalCode.NOT_SEND_SELF); // 자신에게 발송 거절
    }

    public void checkReceiver(UserTb userTb1, UserTb userTb2) throws LgException {
        if (userTb1.getUserId() == userTb2.getUserId()) throw new LgException(GlobalCode.UN_MATCHED_RECEIVER); // 자신에게 발송 거절
    }

    public void neSelf(UserTb userTb1, UserTb userTb2) throws LgException {
        if (!userTb1.equals(userTb2)) throw new LgException(GlobalCode.FAIL_ACCESS);
    }


}
