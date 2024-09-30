package com.kr.lg.module.thirdparty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.common.crypto.AESCrypt;
import com.kr.lg.common.utils.RestPortOne;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.common.enums.entity.flag.AuthFlag;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.exception.ThirdPartyResultCode;
import com.kr.lg.module.thirdparty.model.dto.CertificationsDanalDto;
import com.kr.lg.module.thirdparty.service.DanalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class DanalServiceImpl implements DanalService {

    private final UserRepository userRepository;
    private final RestPortOne restPortOne;


    @Value("${aes.key}")
    private String aesKey;

    @Override
    @Transactional
    public void certificationsDanal(CertificationsDanalDto request, UserTb userTb) throws ThirdPartyException {
        if (!request.getSuccess()) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_CERTIFICATION);
        }
        try {
            HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
            String ci = (String) map.get("unique_key");
            String di = (String)map.get("unique_in_site");
            String name = (String)map.get("name");
            String gender = (String) map.get("gender");
            String birthday = ((String) map.get("birthday")).replace("-", "");

            try {
                log.info("▶ [유저] 유저 본인 인증 정보 등록");
                userRepository.updateAuth(ci, di, name, gender, birthday, AuthFlag.AUTH_STATUS, userTb.getUserId());
            } catch (Exception e) {
              throw new ThirdPartyException(ThirdPartyResultCode.FAIL_INSERT_VERIFY_USER_DATA);
            }
        } catch (ThirdPartyException e) {
            throw e;
        } catch (Exception e) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_CERTIFICATION);
        }
    }

    @Override
    @Transactional
    public String certificationsDanal(CertificationsDanalDto request) throws Exception {
        if (!request.getSuccess()) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> res = restPortOne.getPersonalInfo(request.getImp_uid());
        return AESCrypt.encrypt(new ObjectMapper().writeValueAsString(res), aesKey);
    }
}
