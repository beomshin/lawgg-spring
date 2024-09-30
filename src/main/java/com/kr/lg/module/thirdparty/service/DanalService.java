package com.kr.lg.module.thirdparty.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.dto.CertificationsDanalDto;

public interface DanalService {
    void certificationsDanal(CertificationsDanalDto request, UserTb userTb) throws ThirdPartyException;
    String certificationsDanal(CertificationsDanalDto request) throws Exception;
}
