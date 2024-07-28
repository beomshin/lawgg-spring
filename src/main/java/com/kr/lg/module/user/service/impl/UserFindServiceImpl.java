package com.kr.lg.module.user.service.impl;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.dao.UserDao;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.module.user.mapper.UserMapper;
import com.kr.lg.model.querydsl.AlertQ;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.model.querydsl.UserQ;
import com.kr.lg.module.user.model.res.FindUserIdResponse;
import com.kr.lg.module.user.model.res.FindIFUResponse;
import com.kr.lg.module.user.model.res.FindUAResponse;
import com.kr.lg.module.user.service.UserFindService;
import com.kr.lg.common.utils.RestPortOne;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFindServiceImpl implements UserFindService {

    private final UserDao userDao;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;
    private final RestPortOne restPortOne;

    @Override
    public void checkIdUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException {
        if (!userLayer.getSuccess()) {
            log.error("{}", userLayer.getError_msg());
            throw new LgException(GlobalCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(userLayer.getImp_uid());
        userRepository.findByLoginIdAndCi(userLayer.getLoginId(), (String) map.get("unique_key")).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_ENROLL_ID));
    }

    @Override
    public DefaultResponse findInfoUser(UserLayer userLayer){
        UserQ userQ = userDao.findInfoUser(userLayer);
        return new FindIFUResponse(userQ);
    }

    @Override
    public void checkPwUser(UserLayer userLayer) throws LgException {
        if (!encoder.matches(userLayer.getPassword(), userLayer.getUserTb().getPassword())) throw new LgException(GlobalCode.UN_MATCH_PASSWORD);
    }

    @Override
    public DefaultResponse findUserAlert(UserLayer userLayer) {
        Page<AlertQ> alerts = userDao.findUserAlert(userLayer, PageRequest.of(userLayer.getPage(), userLayer.getPageNum()));
        return new FindUAResponse(alerts);
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
    public List<UserEntry> findUserIds(MapperParam param) throws UserException {
        try {
            log.info("▶ [유저] 유저 포지션 아이디 조회");
            return userMapper.findUserIds(param);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_FIND_USER_BOARDS);
        }
    }
}
