package com.kr.lg.module.thirdparty.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.thirdparty.model.req.DanalCRequest;

public interface ThirdPartyService {

    Boolean certificationsDanal(DanalCRequest request, UserTb userTb) throws LgException;

    String certificationsDanal(DanalCRequest request) throws Exception;
}
