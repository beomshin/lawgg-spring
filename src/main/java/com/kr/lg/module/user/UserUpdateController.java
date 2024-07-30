package com.kr.lg.module.user;

import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.module.user.model.req.UpdateUARequest;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.module.user.model.req.UpdateIURequest;
import com.kr.lg.module.user.model.req.UpdatePURequest;
import com.kr.lg.module.user.model.req.UpdateUPRequest;
import com.kr.lg.module.user.service.UserUpdateService;
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

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserUpdateController {

    private final UserUpdateService userUpdateService;
    private final UserService userService;

    @PostMapping("/api/v1/update/read/user/alerts")
    public ResponseEntity<?> updateUserAlertAll(
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.updateReadUserAlerts(userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

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

    @PostMapping("/api/user/update/alert")
    public ResponseEntity<DefaultResponse> updateUserAlert(
            @RequestBody UpdateUARequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        userUpdateService.updateUserAlert(new UserLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
