package com.kr.lg.module.user.service.impl;

import com.kr.lg.common.crypto.HashNMacUtil;
import com.kr.lg.common.utils.RestPortOne;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.AlertRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.dto.UpdateUserInfoDto;
import com.kr.lg.module.user.model.entry.*;
import com.kr.lg.module.user.model.mapper.FindUserIdParamData;
import com.kr.lg.module.user.model.mapper.FindUserParamData;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.service.UserFindService;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.module.user.service.UserUpdateService;
import com.kr.lg.module.user.sort.UserSort;
import com.kr.lg.service.file.FileService;
import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.UserParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFindService userFindService;
    private final UserUpdateService userUpdateService;
    private final FileService<GlobalFile> fileService;

    private final RestPortOne restPortOne;
    private final UserRepository userRepository;
    private final AlertRepository alertRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public Page<UserBoardEntry> findUserBoards(FindUserBoardsRequest request, UserTb userTb) throws UserException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), UserSort.writeDesc()); // pageable 생성
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .keyword(request.getKeyword())
                .build();
        return userFindService.findUserBoard(new UserParam<>(param, pageable));
    }

    @Override
    public List<UserIdEntry> findUserId(FindUserIdRequest request) throws UserException, LgException {
        if (!request.getSuccess()) {
            throw new UserException(UserResultCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
        String ci = (String) map.get("unique_key");
        MapperParam param = FindUserIdParamData.builder()
                .ci(ci)
                .build();
        List<UserIdEntry> users = userFindService.findUserIds(param);
        if (users.isEmpty()) {
            throw new UserException(UserResultCode.NOT_EXIST_ENROLL_ID);
        }
        return users;
    }

    @Override
    public void verifyUser(VerifyUserRequest request) throws UserException, LgException {
        if (!request.getSuccess()) {
            throw new UserException(UserResultCode.FAIL_CERTIFICATION);
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
        String ci = (String) map.get("unique_key");
        Optional<UserTb> userTb = userRepository.findByLoginIdAndCi(request.getLoginId(), ci);
        if (!userTb.isPresent()) {
            throw new UserException(UserResultCode.NOT_EXIST_ENROLL_ID);
        }

    }

    @Override
    public void verifyPassword(VerifyPasswordRequest request, UserTb userTb) throws UserException {
        if (!encoder.matches(request.getPassword(), userTb.getPassword())) throw new UserException(UserResultCode.UN_MATCH_PASSWORD);
    }

    @Override
    public UserEntry findUser(UserTb userTb) throws UserException {
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .build();
        return userFindService.findUser(param);
    }

    @Override
    public Page<UserAlertEntry> findUserAlerts(FindUserAlertRequest request, UserTb userTb) throws UserException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), UserSort.alertIdDesc()); // pageable 생성
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .keyword(request.getKeyword())
                .build();
        return userFindService.findUserAlerts(new UserParam<>(param, pageable));
    }

    @Override
    @Transactional
    public void updateReadUserAlerts(UserTb userTb) throws UserException {
        Pageable pageable = PageRequest.of(0, 5, UserSort.regDtDesc()); // pageable 생성
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .build();
        List<UserAlertsEntry> userAlerts = userFindService.findTop5Alert(new UserParam<>(param, pageable));
        userUpdateService.updateReadUserAlerts(userAlerts.stream().map(UserAlertsEntry::getAlertId).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void updateReadUserAlert(UpdateUserAlertRequest request, UserTb userTb) throws UserException {
        Optional<AlertTb> alertTb = alertRepository.findByAlertIdAndUserTb_UserId(request.getId(), userTb.getUserId());
        if (alertTb.isPresent()) {
            userUpdateService.updateReadUserAlert(request.getId());
        } else {
            throw new UserException(UserResultCode.NOT_EXIST_ALERT);
        }
    }

    @Override
    @Transactional
    public void updateUserPassword(UpdateUserPasswordRequest request) throws UserException {
        Optional<UserTb> userTb = userRepository.findByLoginId(request.getLoginId());
        if (userTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), userTb.get().getPassword())) throw new UserException(UserResultCode.UN_MATCH_PASSWORD);
            userUpdateService.updateUserPassword(userTb.get(), request.getPassword());
        } else {
            throw new UserException(UserResultCode.NOT_EXIST_USER);
        }
    }

    @Override
    @Transactional
    public void updateUserInfo(UpdateUserInfoRequest request, UserTb userTb) throws UserException, NoSuchAlgorithmException {
        String nickName = userTb.getNickName();
        String password = userTb.getPassword();
        String hashEmail = "";

        Optional<UserTb> overlapUser = userRepository.findByNickName(request.getNickName());
        if (overlapUser.isPresent()) {
            throw new UserException(UserResultCode.OVERLAP_NICK_NAME);
        }

        if (!StringUtils.isBlank(request.getNickName())) nickName = request.getNickName();

        if (!StringUtils.isBlank(request.getPassword())) password = encoder.encode(request.getPassword());

        if (!StringUtils.isBlank(request.getEmail())) {
            hashEmail = HashNMacUtil.getHashSHA256(request.getEmail());
        }

        userUpdateService.updateUserInfo(new UpdateUserInfoDto(userTb.getUserId(), nickName, password, request.getEmail(), hashEmail));
    }

    @Override
    @Transactional
    public String updateUserProfile(UpdateUserProfileRequest request, UserTb userTb) throws UserException {
        GlobalFile globalFile = fileService.uploadSingle(request.getProfile()); // s3 프로필 사진 업로드
        if (globalFile == null) throw new UserException(UserResultCode.FAIL_FILE_UPLOAD); // 업로드 실패
        userUpdateService.updateUserProfile(userTb.getUserId(), globalFile.getPath());
        return globalFile.getPath();
    }

}
