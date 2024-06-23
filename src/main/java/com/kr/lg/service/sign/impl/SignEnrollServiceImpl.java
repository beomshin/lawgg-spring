package com.kr.lg.service.sign.impl;

import com.kr.lg.db.entities.TierTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.enums.entity.element.AuthEnum;
import com.kr.lg.db.repositories.TierRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.layer.SignLayer;
import com.kr.lg.service.sign.SignEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignEnrollServiceImpl implements SignEnrollService {

    private final UserRepository userRepository;
    private final TierRepository tierRepository;
    private final BCryptPasswordEncoder encoder;
    private final String BRONZE_3 = "Bronze_3";

    @Override
    public void userSign(SignLayer signLayer) throws Exception {
        if (userRepository.findByLoginId(signLayer.getLoginId()).isPresent()) throw new LgException(GlobalCode.GC10001);
        TierTb tierTb = tierRepository.findByKey(BRONZE_3);
        userRepository.save(UserTb.builder()
                .loginId(signLayer.getLoginId())
                .password(encoder.encode(signLayer.getPassword()))
                .nickName(signLayer.getNickName())
                .personalPeriod(signLayer.getPersonalPeriod())
                .snsType(signLayer.getSnsType())
                .authFlag(AuthEnum.NON_AUTH_STATUS)
                .tierId(tierTb)
                .build());
    }
}
