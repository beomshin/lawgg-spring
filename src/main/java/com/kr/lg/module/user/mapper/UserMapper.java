package com.kr.lg.module.user.mapper;

import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.entry.UserIdEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserBoardEntry> findBoards(UserParam<?> param);
    long findBoardsCnt(MapperParam param);
    List<UserIdEntry> findUserIds(MapperParam param);
    UserEntry findUser(MapperParam param);
    List<UserAlertEntry> findUserAlerts(UserParam<?> param);
    long findUserAlertsCnt(MapperParam param);
}
