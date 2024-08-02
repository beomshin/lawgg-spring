package com.kr.lg.module.user;

import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.res.UpdateUPResponse;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.web.dto.annotation.UserAdapter;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.module.user.model.req.UpdateUserAlertRequest;
import com.kr.lg.module.user.model.req.UpdateUserInfoRequest;
import com.kr.lg.module.user.model.req.UpdateUserPasswordRequest;
import com.kr.lg.module.user.model.req.UpdateUserProfileRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserUpdateController {

    private final UserService userService;

    @PostMapping("/api/v1/update/read/user/alerts")
    @ApiOperation(value = "회원 알림 리스트 업데이트", notes = "회원 알림 리스트를 업데이트합니다.")
    public ResponseEntity<?> updateUserAlertAll(
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.updateReadUserAlerts(userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/public/v1/update/user/password")
    @ApiOperation(value = "회원 비밀번호 업데이트", notes = "회원 비밀번호 업데이트합니다.")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody @Valid UpdateUserPasswordRequest request
    ) throws UserException {
        userService.updateUserPassword(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/update/user/info")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
    public ResponseEntity<?> updateUserInfo(
            @RequestBody @Valid UpdateUserInfoRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException, NoSuchAlgorithmException {
        userService.updateUserInfo(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/V1/update/user/profile")
    @ApiOperation(value = "회원 프로필 수정", notes = "회원 프로필 정보를 수정합니다.")
    public ResponseEntity<?> updateUserProfile(
            @ModelAttribute @Valid UpdateUserProfileRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws  UserException {
        String profile = userService.updateUserProfile(request, userAdapter.getUserTb());
        AbstractSpec spec = UpdateUPResponse.builder()
                .profile(profile)
                .build();
        return ResponseEntity.ok(spec);
    }

    @PostMapping("/api/v1/update/user/alert")
    public ResponseEntity<?> updateUserAlert(
            @RequestBody @Valid UpdateUserAlertRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.updateReadUserAlert(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }
}
