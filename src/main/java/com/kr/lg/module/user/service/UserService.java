package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.model.net.request.sign.SignURequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.entry.UserIdEntry;
import com.kr.lg.module.user.model.req.*;
import org.springframework.data.domain.Page;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    Page<UserBoardEntry> findUserBoards(FindUserBoardsRequest request , UserTb userTb) throws UserException;
    List<UserIdEntry> findUserId(FindUserIdRequest request) throws UserException, LgException;
    void verifyUser(VerifyUserRequest request) throws UserException, LgException;
    void verifyPassword(VerifyPasswordRequest request, UserTb userTb) throws UserException;
    UserEntry findUser(UserTb userTb) throws UserException;
    Page<UserAlertEntry> findUserAlerts(FindUserAlertRequest request, UserTb userTb) throws UserException;
    void updateReadUserAlerts(UserTb userTb) throws UserException;
    void updateReadUserAlert(UpdateUserAlertRequest request, UserTb userTb) throws UserException;
    void updateUserPassword(UpdateUserPasswordRequest request) throws  UserException;
    void updateUserInfo(UpdateUserInfoRequest request, UserTb userTb) throws UserException, NoSuchAlgorithmException;
    String updateUserProfile(UpdateUserProfileRequest request, UserTb userTb) throws  UserException;
    UserTb enrollUser(SignURequest request) throws UserException;
}
