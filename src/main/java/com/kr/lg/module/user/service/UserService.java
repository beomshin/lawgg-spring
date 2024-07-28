package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.req.FindUserIdRequest;
import com.kr.lg.module.user.model.req.FindUserBoardsRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserBoardEntry> findUserBoards(FindUserBoardsRequest request , UserTb userTb) throws UserException;
    List<UserEntry> findUserId(FindUserIdRequest request) throws UserException, LgException;
}
