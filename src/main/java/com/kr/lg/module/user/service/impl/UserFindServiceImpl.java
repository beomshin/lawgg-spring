package com.kr.lg.module.user.service.impl;

import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.entry.*;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import com.kr.lg.module.user.mapper.UserMapper;
import com.kr.lg.module.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFindServiceImpl implements UserFindService {

    private final UserMapper userMapper;

    @Override
    public UserEntry findUser(MapperParam param) throws UserException {
        try {
            log.info("▶ [유저] 유저 조회");
            return userMapper.findUser(param);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER);
        }
    }

    @Override
    public Page<UserBoardEntry> findUserBoard(UserParam<?> param) throws UserException {
        try {
            log.info("▶ [유저] 유저 포지션 게시판 조회");
            List<UserBoardEntry> content = userMapper.findBoards(param);
            long count = userMapper.findBoardsCnt(param.getData()); // board 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER_BOARDS);
        }
    }

    @Override
    public List<UserIdEntry> findUserIds(MapperParam param) throws UserException {
        try {
            log.info("▶ [유저] 유저 포지션 아이디 조회");
            return userMapper.findUserIds(param);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER_BOARDS);
        }
    }

    @Override
    public Page<UserAlertEntry> findUserAlerts(UserParam<?> param) throws UserException {
        try {
            log.info("▶ [유저] 유저 알림 리스트 조회");
            List<UserAlertEntry> content = userMapper.findUserAlerts(param);
            long count = userMapper.findUserAlertsCnt(param.getData()); // 알림 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER_ALERTS);
        }
    }

    @Override
    public List<UserAlertsEntry> findTop5Alert(UserParam<?> param) throws UserException {
        try {
            log.info("▶ [유저] 유저 알림 리스트 탑 5 조회");
            return userMapper.findTop5Alert(param);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER_ALERTS);
        }
    }
}
