package com.kr.lg.dao;

import com.kr.lg.model.web.querydsl.AlertQ;
import com.kr.lg.model.web.querydsl.UserQ;
import com.kr.lg.model.web.common.layer.UserLayer;
import com.kr.lg.entities.AlertTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {

    List<UserQ> findIdUser(UserLayer requestDto);
    UserQ findInfoUser(UserLayer requestDto);
    Page<AlertQ> findUserAlert(UserLayer requestDto, Pageable pageable);
    List<AlertTb> findTop5Alert(UserLayer requestDto, Pageable pageable);
}
