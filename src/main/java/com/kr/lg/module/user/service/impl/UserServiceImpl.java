package com.kr.lg.module.user.service.impl;

import com.kr.lg.common.utils.RestPortOne;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.mapper.FindUserIdParamData;
import com.kr.lg.module.user.model.mapper.FindUserParamData;
import com.kr.lg.module.user.model.req.FindUserIdRequest;
import com.kr.lg.module.user.model.req.FindUserBoardsRequest;
import com.kr.lg.module.user.service.UserFindService;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFindService userFindService;
    private final RestPortOne restPortOne;


    @Override
    public Page<UserBoardEntry> findUserBoards(FindUserBoardsRequest request, UserTb userTb) throws UserException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum()); // pageable 생성
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .keyword(request.getKeyword())
                .build();
        return userFindService.findUserBoard(new UserParam<>(param, pageable));
    }

    @Override
    public List<UserEntry> findUserId(FindUserIdRequest request) throws UserException, LgException {
        if (!request.getSuccess()) {
            throw new UserException(UserResultCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
        String ci = (String) map.get("unique_key");
        MapperParam param = FindUserIdParamData.builder()
                .ci(ci)
                .build();
        List<UserEntry> users = userFindService.findUserIds(param);
        if (users.isEmpty()) {
            throw new UserException(UserResultCode.NOT_EXIST_ENROLL_ID);
        }
        return users;
    }

}
