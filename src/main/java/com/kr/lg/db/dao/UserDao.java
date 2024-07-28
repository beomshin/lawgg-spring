package com.kr.lg.db.dao;

import com.kr.lg.model.querydsl.AlertQ;
import com.kr.lg.model.querydsl.UserQ;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.db.entities.AlertTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {

    UserQ findInfoUser(UserLayer requestDto);
    Page<AlertQ> findUserAlert(UserLayer requestDto, Pageable pageable);
    List<AlertTb> findTop5Alert(UserLayer requestDto, Pageable pageable);
}
