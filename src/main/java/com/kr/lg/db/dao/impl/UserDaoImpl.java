package com.kr.lg.db.dao.impl;

import com.kr.lg.db.dao.UserDao;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.db.query.UserQuery;
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
    public List<AlertTb> findTop5Alert(UserLayer requestDto, Pageable pageable) {
        return userQuery.findTop5Alert(requestDto.getUserTb().getUserId(), pageable).fetch();
    }
}
