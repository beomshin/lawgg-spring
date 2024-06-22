package com.kr.lg.dao.impl;

import com.kr.lg.dao.UserDao;
import com.kr.lg.entities.AlertTb;
import com.kr.lg.model.web.querydsl.AlertQ;
import com.kr.lg.model.web.common.layer.UserLayer;
import com.kr.lg.model.web.querydsl.UserQ;
import com.kr.lg.query.UserQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserDaoImpl implements UserDao {

    private final UserQuery userQuery;

    @Override
    public List<UserQ> findIdUser(UserLayer requestDto) {
        return userQuery.findIdUser(requestDto).fetch();
    }

    @Override
    public UserQ findInfoUser(UserLayer requestDto) {
        return userQuery.findInfoUser(requestDto).fetchOne();
    }

    @Override
    public Page<AlertQ> findUserAlert(UserLayer requestDto, Pageable pageable) {
        JPAQuery<AlertQ> content = userQuery.findUserAlert(requestDto, pageable);
        JPAQuery<Long> count = userQuery.findUserAlertCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public List<AlertTb> findTop5Alert(UserLayer requestDto, Pageable pageable) {
        return userQuery.findTop5Alert(requestDto.getUserTb().getUserId(), pageable).fetch();
    }
}
