package com.kr.lg.service.user;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.UserLayer;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Transactional(readOnly = true)
public interface UserUpdateService {

    @Transactional
    void updatePwUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException;
    @Transactional
    void updateInfoUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException;
    @Transactional
    DefaultResponse updateUserProfile(UserLayer userLayer) throws LgException;
    @Transactional
    void updateUserAlertAll(UserLayer userLayer) throws LgException;
    @Transactional
    void updateUserAlert(UserLayer userLayer) throws LgException;
}
