package com.kr.lg.controller.user;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.net.request.user.UpdateUARequest;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.model.net.request.user.UpdateIURequest;
import com.kr.lg.model.net.request.user.UpdatePURequest;
import com.kr.lg.model.net.request.user.UpdateUPRequest;
import com.kr.lg.service.user.UserUpdateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @PostMapping("/api/public/user/update/pw")
    @ApiOperation(value = "회원 비밀번호 업데이트", notes = "회원 비밀번호 업데이트합니다.")
    public ResponseEntity<DefaultResponse> updatePwUser(
            @RequestBody UpdatePURequest request
    ) throws LgException, NoSuchAlgorithmException {
        userUpdateService.updatePwUser(new UserLayer(request));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/user/update/info")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
    public ResponseEntity<DefaultResponse> updateInfoUser(
            @RequestBody UpdateIURequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException, NoSuchAlgorithmException {
        userUpdateService.updateInfoUser(new UserLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/user/update/profile")
    @ApiOperation(value = "회원 프로필 수정", notes = "회원 프로필 정보를 수정합니다.")
    public ResponseEntity<DefaultResponse> updateUserProfile(
            @ModelAttribute UpdateUPRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = userUpdateService.updateUserProfile(new UserLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/user/update/alert/all")
    public ResponseEntity<DefaultResponse> updateUserAlertAll(
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        userUpdateService.updateUserAlertAll(new UserLayer(userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/user/update/alert")
    public ResponseEntity<DefaultResponse> updateUserAlert(
            @RequestBody UpdateUARequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        userUpdateService.updateUserAlert(new UserLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
