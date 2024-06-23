package com.kr.lg.service.sign.impl;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.db.repositories.NickNameRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.SignLayer;
import com.kr.lg.service.sign.SignCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignCheckServiceImpl implements SignCheckService {

    private final UserRepository userRepository;
    private final NickNameRepository nickNameRepository;

    @Override
    public void checkIdSign(SignLayer signLayer) throws LgException {
        if (userRepository.findByLoginId(signLayer.getLoginId()).isPresent()) {
            throw new LgException(GlobalCode.GC10001);
        }
    }

    @Override
    public void checkNickNameSign(SignLayer signLayer) throws LgException {
        if (userRepository.findByNickName(signLayer.getNickName()).isPresent()) {
            throw new LgException(GlobalCode.OVERLAP_NICK_NAME);
        } else if (nickNameRepository.findByName(signLayer.getNickName()).isPresent()) {
            throw new LgException(GlobalCode.OVERLAP_NICK_NAME);
        }
    }
}
