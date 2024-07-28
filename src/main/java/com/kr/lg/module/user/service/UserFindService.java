package com.kr.lg.module.user.service;

import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.web.dto.mapper.BoardParam;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.UserLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Transactional(readOnly = true)
public interface UserFindService {
    void checkIdUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException;
    DefaultResponse findInfoUser(UserLayer userLayer) throws LgException;
    void checkPwUser(UserLayer userLayer) throws LgException;
    DefaultResponse findUserAlert(UserLayer userLayer) throws LgException;
    Page<UserBoardEntry> findUserBoard(UserParam<?> param) throws UserException;
    List<UserEntry> findUserIds(MapperParam param) throws UserException;
}
