package com.kr.lg.module.user.service;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.UserLayer;
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
