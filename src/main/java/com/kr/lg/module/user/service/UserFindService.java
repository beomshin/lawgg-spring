package com.kr.lg.module.user.service;

import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.*;
import com.kr.lg.model.mapper.UserParam;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserFindService {
    Page<UserBoardEntry> findUserBoard(UserParam<?> param) throws UserException;
    Page<UserAlertEntry> findUserAlerts(UserParam<?> param) throws UserException;
    List<UserAlertsEntry> findTop5Alert(UserParam<?> param) throws UserException;
}
