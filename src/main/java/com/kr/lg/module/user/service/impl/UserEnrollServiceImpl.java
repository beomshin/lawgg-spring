package com.kr.lg.module.user.service.impl;


import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.dto.EnrollUserDto;

import com.kr.lg.module.user.service.UserEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEnrollServiceImpl implements UserEnrollService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public UserTb enrollUser(EnrollUserDto enrollUserDto) throws UserException {
        try {
            log.info("▶ [유저] 유저 등록");
            return userRepository.save(UserTb.builder()
                    .loginId(enrollUserDto.getLoginId())
                    .password(StringUtils.isBlank(enrollUserDto.getPassword()) ? null : encoder.encode(enrollUserDto.getPassword()))
                    .nickName(enrollUserDto.getNickName())
                    .personalPeriod(enrollUserDto.getPersonalPeriod())
                    .snsType(enrollUserDto.getSnsType())
                    .authFlag(enrollUserDto.getAuthFlag())
                    .tierTb(enrollUserDto.getTierTb())
                    .build());
        } catch (Exception e) {
            throw new UserException(UserResultCode.FAIL_ENROLL_USER);
        }
    }
}
