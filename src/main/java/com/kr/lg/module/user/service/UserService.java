package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.login.model.dto.LoginDto;
import com.kr.lg.module.user.model.req.EnrollUserRequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.req.*;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserBoardEntry> findUserBoards(FindMyBoardsRequest request , UserTb userTb) throws UserException;
    Page<UserAlertEntry> findUserAlerts(FindMyAlertRequest request, UserTb userTb) throws UserException;
    void updateReadUserAlerts(UserTb userTb) throws UserException;
    void updateReadUserAlert(UpdateUserAlertRequest request, UserTb userTb) throws UserException;
    void updateUserPassword(UpdateUserPasswordRequest request, UserTb userTb) throws  UserException;
    void updateUserProfile(UpdateUserProfileRequest request, UserTb userTb) throws  UserException;
    UserTb enrollUser(EnrollUserRequest request) throws UserException;
    UserTb enrollUser(LoginDto loginDto) throws UserException;
    void updateSessionUserTb(UserTb userTb);
    void checkId(String id) throws UserException;
}
