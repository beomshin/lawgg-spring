package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.entry.UserIdEntry;
import com.kr.lg.module.user.model.req.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserBoardEntry> findUserBoards(FindUserBoardsRequest request , UserTb userTb) throws UserException;
    List<UserIdEntry> findUserId(FindUserIdRequest request) throws UserException, LgException;
    void verifyUser(VerifyUserRequest request) throws UserException, LgException;
    void verifyPassword(VerifyPasswordRequest request, UserTb userTb) throws UserException;
    UserEntry findUser(UserTb userTb) throws UserException;
    Page<UserAlertEntry> findUserAlerts(FindUserAlertRequest request, UserTb userTb) throws UserException;
    void updateReadUserAlerts(UserTb userTb) throws UserException;
}
