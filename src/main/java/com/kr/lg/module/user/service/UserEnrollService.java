package com.kr.lg.module.user.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.dto.EnrollUserDto;

public interface UserEnrollService {
    UserTb enrollUser(EnrollUserDto enrollUserDto) throws UserException;
}
