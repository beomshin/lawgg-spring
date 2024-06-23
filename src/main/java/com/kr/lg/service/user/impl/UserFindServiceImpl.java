package com.kr.lg.service.user.impl;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.dao.UserDao;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.db.mapper.UserMapper;
import com.kr.lg.web.querydsl.AlertQ;
import com.kr.lg.web.mapper.UBoardMapper;
import com.kr.lg.web.common.layer.UserLayer;
import com.kr.lg.web.querydsl.UserQ;
import com.kr.lg.web.net.response.user.FindIUResponse;
import com.kr.lg.web.net.response.user.FindIFUResponse;
import com.kr.lg.web.net.response.user.FindUAResponse;
import com.kr.lg.web.net.response.user.FindUBResponse;
import com.kr.lg.service.user.UserFindService;
import com.kr.lg.utils.RestPortOne;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    public DefaultResponse findIdUser(UserLayer userLayer) throws LgException {
        if (!userLayer.getSuccess()) {
            log.error("{}", userLayer.getError_msg());
            throw new LgException(GlobalCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(userLayer.getImp_uid());
        userLayer.setCi((String) map.get("unique_key"));
        List<UserQ> ids = userDao.findIdUser(userLayer); // 아이디 조회
        if (ids.size() == 0) throw new LgException(GlobalCode.NOT_EXIST_ENROLL_ID); // 아이디 없는경우
        return new FindIUResponse(ids);
    }

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
    public DefaultResponse findUserBoards(UserLayer userLayer) throws LgException {
        List<UBoardMapper> boards = userMapper.findUserBoards(userLayer.getUserTb().getUserId(), userLayer.getPageNum(), userLayer.getPage() * userLayer.getPageNum(), userLayer.getKeyword());
        Integer totalElements = userMapper.findUserBoardsCount(userLayer.getUserTb().getUserId(), userLayer.getKeyword()) ;
        Integer totalPage = totalElements % userLayer.getPageNum() == 0 ? totalElements / userLayer.getPageNum() : totalElements / userLayer.getPageNum()  + 1;
        return new FindUBResponse(boards, totalElements, totalPage, userLayer.getPage());
    }

    @Override
    public DefaultResponse findUserAlert(UserLayer userLayer) {
        Page<AlertQ> alerts = userDao.findUserAlert(userLayer, PageRequest.of(userLayer.getPage(), userLayer.getPageNum()));
        return new FindUAResponse(alerts);
    }
}
