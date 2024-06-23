package com.kr.lg.service.user;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.layer.UserLayer;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Transactional(readOnly = true)
public interface UserFindService {
    DefaultResponse findIdUser(UserLayer userLayer) throws LgException;
    void checkIdUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException;
    DefaultResponse findInfoUser(UserLayer userLayer) throws LgException;
    void checkPwUser(UserLayer userLayer) throws LgException;
    DefaultResponse findUserBoards(UserLayer userLayer) throws LgException;
    DefaultResponse findUserAlert(UserLayer userLayer) throws LgException;
}
