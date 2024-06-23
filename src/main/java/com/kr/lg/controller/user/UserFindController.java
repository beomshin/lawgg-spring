package com.kr.lg.controller.user;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.layer.UserLayer;
import com.kr.lg.service.user.UserFindService;
import com.kr.lg.web.net.request.user.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserFindService userFindService;

    @PostMapping("/api/public/user/find/id")
    @ApiOperation(value = "회원 아이디 조회", notes = "회원 아이디 정보를 조회합니다.")
    public ResponseEntity<DefaultResponse> findIdUser(
            @RequestBody FindIURequest request
    ) throws LgException, NoSuchAlgorithmException {
        DefaultResponse body = userFindService.findIdUser(new UserLayer(request));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/public/user/check/id")
    @ApiOperation(value = "회원 이메일&아이디 인증 조회", notes = "회원 이메일&아이디 인증 조회합니다.")
    public ResponseEntity<DefaultResponse> checkIdUser(
            @RequestBody CheckIURequest request
    ) throws LgException, NoSuchAlgorithmException {
        userFindService.checkIdUser(new UserLayer(request));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/user/check/pw")
    @ApiOperation(value = "회원 비밀번호 인증", notes = "회원 비밀번호 인증을 합니다.")
    public ResponseEntity<DefaultResponse> checkPwUser(
            @RequestBody CheckPURequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        userFindService.checkPwUser(new UserLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/user/find/info")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회합니다.")
    public ResponseEntity<DefaultResponse> findInfoUser(
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = userFindService.findInfoUser(new UserLayer(userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/user/find/boards")
    @ApiOperation(value = "회원 게시판 조회", notes = "회원 게시판을 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserBoards(
            FindUBRequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = userFindService.findUserBoards(new UserLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/user/find/alert")
    public ResponseEntity<DefaultResponse> findUserAlert(
            FindUARequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = userFindService.findUserAlert(new UserLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }
}
