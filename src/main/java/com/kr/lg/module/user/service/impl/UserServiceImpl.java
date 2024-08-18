package com.kr.lg.module.user.service.impl;

import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.common.utils.LoginUtils;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.db.entities.TierTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.AlertRepository;
import com.kr.lg.db.repositories.TierRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.common.enums.entity.flag.AuthFlag;
import com.kr.lg.module.login.model.dto.LoginDto;
import com.kr.lg.module.user.model.req.EnrollUserRequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.excpetion.UserResultCode;
import com.kr.lg.module.user.model.dto.EnrollUserDto;
import com.kr.lg.module.user.model.entry.*;
import com.kr.lg.module.user.model.mapper.FindUserParamData;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.service.UserEnrollService;
import com.kr.lg.module.user.service.UserFindService;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.module.user.service.UserUpdateService;
import com.kr.lg.module.user.sort.UserSort;
import com.kr.lg.module.thirdparty.service.FileService;
import com.kr.lg.model.dto.FileDto;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.model.mapper.UserParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFindService userFindService;
    private final UserUpdateService userUpdateService;
    private final UserEnrollService userEnrollService;
    private final FileService<FileDto> fileService;

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final TierRepository tierRepository;

    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Page<UserBoardEntry> findUserBoards(FindMyBoardsRequest request, UserTb userTb) throws UserException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), UserSort.writeDesc()); // pageable 생성
        MapperParam param = FindUserParamData.builder()
                .userId(userTb.getUserId())
                .keyword(request.getKeyword())
                .build();
        return userFindService.findUserBoard(new UserParam<>(param, pageable));
    }

    @Override
    public Page<UserAlertEntry> findUserAlerts(FindMyAlertRequest request, UserTb userTb) throws UserException {
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
    public void updateUserPassword(UpdateUserPasswordRequest request, UserTb userTb) throws UserException {
        Optional<UserTb> userTb1 = userRepository.findById(userTb.getUserId());
        if (userTb1.isPresent()) {
            if (!encoder.matches(request.getOldPassword(), userTb1.get().getPassword())) throw new UserException(UserResultCode.UN_MATCH_PASSWORD);
            userUpdateService.updateUserPassword(userTb1.get(), request.getNewPassword());
            this.updateSessionUserTb(userTb);
        } else {
            throw new UserException(UserResultCode.NOT_EXIST_USER);
        }
    }

    @Override
    @Transactional
    public void updateUserProfile(UpdateUserProfileRequest request, UserTb userTb) throws UserException {
        FileDto fileDto = fileService.uploadSingle(request.getProfile()); // s3 프로필 사진 업로드
        if (fileDto == null) throw new UserException(UserResultCode.FAIL_FILE_UPLOAD); // 업로드 실패
        userUpdateService.updateUserProfile(userTb.getUserId(), fileDto.getPath());
        this.updateSessionUserTb(userTb);
    }

    @Override
    public UserTb enrollUser(EnrollUserRequest request) throws UserException {
        Optional<UserTb> userTb = userRepository.findByLoginId(request.getLoginId());
        if (!userTb.isPresent()) {
            TierTb tierTb = tierRepository.findByKey("Bronze_3");
            return userEnrollService.enrollUser(EnrollUserDto.builder()
                            .loginId(request.getLoginId())
                            .password(request.getPassword())
                            .nickName(request.getNickName())
                            .personalPeriod(request.getPersonalPeriod())
                            .snsType(SnsType.LG_SNS_TYPE)
                            .authFlag(AuthFlag.NON_AUTH_STATUS)
                            .tierTb(tierTb)
                    .build());
        } else {
            throw new UserException(UserResultCode.ALREADY_ENROLL_USER);

        }

    }

    @Override
    public UserTb enrollUser(LoginDto loginDto) throws UserException {
        Optional<UserTb> userTb = userRepository.findBySnsIdAndSnsType(loginDto.getSnsId(), loginDto.getSnsType());
        if (!userTb.isPresent()) {
            TierTb tierTb = tierRepository.findByKey("Bronze_3");
            return userEnrollService.enrollUser(EnrollUserDto.builder()
                    .loginId(LoginUtils.getLoginId(loginDto.getSnsType()))
                    .snsId(loginDto.getSnsId())
                    .nickName(loginDto.getNickname())
                    .email(loginDto.getEmail())
                    .name(loginDto.getName())
                    .profile(loginDto.getProfile())
                    .snsType(loginDto.getSnsType())
                    .tierTb(tierTb)
                    .build());
        } else {
            return userTb.get();
        }
    }

    @Override
    public void updateSessionUserTb(UserTb userTb) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTb.getLoginId(), userTb.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
