package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.dto.UpdateUserInfoDto;

import java.util.List;

public interface UserUpdateService {

    void updateReadUserAlerts(List<Long> alerts) throws UserException;
    void updateReadUserAlert(long alertId) throws UserException;
    void updateUserPassword(UserTb userTb, String password) throws UserException;
    void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) throws UserException;
    void updateUserProfile(long userId, String path) throws UserException;
}
