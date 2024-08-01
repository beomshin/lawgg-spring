package com.kr.lg.module.thirdparty.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.req.CertificationsDanalRequest;

public interface DanalService {
    void certificationsDanal(CertificationsDanalRequest request, UserTb userTb) throws ThirdPartyException;
    String certificationsDanal(CertificationsDanalRequest request) throws Exception;
}
