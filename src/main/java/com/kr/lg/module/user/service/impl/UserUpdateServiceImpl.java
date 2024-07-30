package com.kr.lg.module.user.service.impl;

import com.kr.lg.common.crypto.HashNMacUtil;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.AlertRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.service.file.FileService;
import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.module.user.model.res.UpdateUPResponse;
import com.kr.lg.module.user.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUpdateServiceImpl implements UserUpdateService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final FileService<GlobalFile> fileService;
    private final AlertRepository alertRepository;

    @Override
    @Transactional
    public void updatePwUser(UserLayer userLayer) throws LgException {
        UserTb userTb = userRepository.findByLoginId(userLayer.getLoginId()).orElseThrow(() -> new LgException(GlobalCode.FAIL_UPDATE_PASSWORD)); // 아이디 조회
        if (encoder.matches(userLayer.getPassword(), userTb.getPassword())) throw new LgException(GlobalCode.MATCH_PASSWORD); // 비밀번호 검사
        userRepository.updatePassword(userTb.getUserId(), encoder.encode(userLayer.getPassword()));
    }

    @Override
    public void updateInfoUser(UserLayer userLayer) throws LgException, NoSuchAlgorithmException {
        UserTb userTb = userLayer.getUserTb();
        String nickName = userTb.getNickName();
        String password = userTb.getPassword();
        String hashEmail = "";

        if (!nickName.equals(userLayer.getNickName()) && userRepository.findByNickName(userLayer.getNickName()).isPresent()) throw new LgException(GlobalCode.OVERLAP_NICK_NAME);

        if (!StringUtils.isBlank(userLayer.getNickName())) nickName = userLayer.getNickName();

        if (!StringUtils.isBlank(userLayer.getPassword())) password = encoder.encode(userLayer.getPassword());

        if (!StringUtils.isBlank(userLayer.getEmail())) {
            hashEmail = HashNMacUtil.getHashSHA256(userLayer.getEmail());
        }

        userRepository.updateUser(userTb.getUserId(), password, nickName, userLayer.getEmail(), hashEmail);
    }

    @Override
    public DefaultResponse updateUserProfile(UserLayer userLayer) throws LgException {
        UserTb userTb = userLayer.getUserTb();
        if (userLayer.getProfile() != null) { // 프로필 수정 경우
            GlobalFile globalFile = fileService.uploadSingle(userLayer.getProfile()); // s3 프로필 사진 업로드
            if (globalFile == null) throw new LgException(GlobalCode.FAIL_FILE_UPLOAD); // 업로드 실패
            userRepository.updateProfile(userTb.getUserId(), globalFile.getPath());
        }
        return new UpdateUPResponse(userTb.getProfile());
    }

    @Override
    public void updateUserAlert(UserLayer userLayer) throws LgException {
        AlertTb alertTb = alertRepository.findById(userLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_ALERT));
        alertRepository.readAlert(alertTb.getAlertId());
    }
}
