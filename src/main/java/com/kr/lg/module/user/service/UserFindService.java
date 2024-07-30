package com.kr.lg.module.user.service;

import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.entry.UserIdEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserFindService {
    UserEntry findUser(MapperParam param) throws UserException;
    Page<UserBoardEntry> findUserBoard(UserParam<?> param) throws UserException;
    List<UserIdEntry> findUserIds(MapperParam param) throws UserException;
    Page<UserAlertEntry> findUserAlerts(UserParam<?> param) throws UserException;

}
