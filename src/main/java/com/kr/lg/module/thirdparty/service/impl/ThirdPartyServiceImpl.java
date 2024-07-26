package com.kr.lg.module.thirdparty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.common.crypto.AESCrypt;
import com.kr.lg.common.utils.RestPortOne;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.enums.AuthEnum;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.thirdparty.model.req.DanalCRequest;
import com.kr.lg.module.thirdparty.service.ThirdPartyService;
import com.kr.lg.web.dto.global.GlobalCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThirdPartyServiceImpl implements ThirdPartyService {

    private final UserRepository userRepository;
    private final RestPortOne restPortOne;

    @Value("${portone.rest.key}")
    private String portoneKey;

    @Value("${portone.rest.secret}")
    private String portoneSecret;

    @Value("${aes.key}")
    private String aesKey;

    @Override
    @Transactional
    public Boolean certificationsDanal(DanalCRequest request, UserTb userTb) throws LgException {
        log.info("▶ [Third Party] certificationsDanal 메소드 실행");

        if (!request.getSuccess()) {
            log.error("{}", request.getError_msg());
            return false;
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
        userRepository.updateAuth((String) map.get("unique_key"), (String)map.get("unique_in_site")
                , (String)map.get("name"), (String) map.get("gender"), ((String) map.get("birthday")).replace("-", ""), AuthEnum.AUTH_STATUS, userTb.getUserId());
        return true;
    }

    @Override
    @Transactional
    public String certificationsDanal(DanalCRequest request) throws Exception {
        log.info("▶ [Third Party] certificationsDanal 메소드 실행");

        if (!request.getSuccess()) {
            log.error("{}", request.getError_msg());
            throw new LgException(GlobalCode.FAIL_CERTIFICATION);
        }
        return AESCrypt.encrypt(new ObjectMapper().writeValueAsString(restPortOne.getPersonalInfo(request.getImp_uid())), aesKey);
    }
}
