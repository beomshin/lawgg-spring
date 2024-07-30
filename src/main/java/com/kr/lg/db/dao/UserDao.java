package com.kr.lg.db.dao;

import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.db.entities.AlertTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDao {

    List<AlertTb> findTop5Alert(UserLayer requestDto, Pageable pageable);
}
