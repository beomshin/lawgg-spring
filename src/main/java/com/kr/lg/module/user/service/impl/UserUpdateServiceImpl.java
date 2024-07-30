package com.kr.lg.module.user.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.AlertRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.dto.UpdateUserInfoDto;
import com.kr.lg.module.user.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUpdateServiceImpl implements UserUpdateService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AlertRepository alertRepository;

    @Override
    @Transactional
    public void updateReadUserAlerts(List<Long> alerts) throws UserException {
        try {
            log.info("▶ [유저] 유저 알림 리스트 읽기 업데이트");
            alertRepository.readAlertAll(alerts);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_UPDATE_USER_ALERT_LIST);
        }
    }

    @Override
    @Transactional
    public void updateReadUserAlert(long alertId) throws UserException {
        try {
            log.info("▶ [유저] 유저 알림 읽기 업데이트");
            alertRepository.readAlert(alertId);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_UPDATE_USER_ALERT);
        }
    }

    @Override
    @Transactional
    public void updateUserPassword(UserTb userTb, String password) throws UserException {
        try {
            log.info("▶ [유저] 유저 패스워드 업데이트");
            userRepository.updatePassword(userTb.getUserId(), encoder.encode(password));
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_UPDATE_USER_PASSWORD);
        }
    }

    @Override
    @Transactional
    public void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) throws UserException {
        try {
            log.info("▶ [유저] 유저 정보 업데이트");
            userRepository.updateUserInfo(updateUserInfoDto.getUserId(), updateUserInfoDto.getPassword(), updateUserInfoDto.getNickName(), updateUserInfoDto.getEmail(), updateUserInfoDto.getHashEmail());
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_UPDATE_USER_INFO);
        }
    }

    @Override
    @Transactional
    public void updateUserProfile(long userId, String path) throws UserException {
        try {
            log.info("▶ [유저] 유저 프로필 업데이트");
            userRepository.updateProfile(userId, path);
        } catch (Exception e) {
            log.error("", e);
            throw new UserException(UserResultCode.FAIL_UPDATE_USER_PROFILE);
        }
    }

}
