package com.kr.lg.module.user;

import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.entry.UserIdEntry;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.model.res.FindUserInfoResponse;
import com.kr.lg.module.user.model.res.FindUserAlertResponse;
import com.kr.lg.module.user.model.res.FindUserBoardsResponse;
import com.kr.lg.module.user.model.res.FindUserIdResponse;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.web.dto.annotation.UserAdapter;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserService userService;

    @PostMapping("/api/public/v1/find/user/id")
    @ApiOperation(value = "회원 아이디 조회", notes = "회원 아이디 정보를 조회합니다.")
    public ResponseEntity<?> findUserId(
            @RequestBody @Valid FindUserIdRequest request
    ) throws UserException {
        List<UserIdEntry> ids = userService.findUserId(request);
        AbstractSpec spec = FindUserIdResponse.builder()
                .ids(ids)
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/v1/find/find/user/boards")
    @ApiOperation(value = "회원 게시판 조회", notes = "회원 게시판을 조회합니다.")
    public ResponseEntity<?> findUserBoards(
            @Valid FindUserBoardsRequest requestDto,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        Page<UserBoardEntry> boards = userService.findUserBoards(requestDto, userAdapter.getUserTb());
        AbstractSpec spec = FindUserBoardsResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .curPage(boards.getNumber())
                .totalPage(boards.getTotalPages())
                .build();
        return ResponseEntity.ok(spec);
    }

    @PostMapping("/api/public/v1/verify/user")
    @ApiOperation(value = "회원 인증", notes = "회원 인증합니다.")
    public ResponseEntity<?> verifyUser(
            @RequestBody @Valid VerifyUserRequest request
    ) throws UserException {
        userService.verifyUser(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/verify/password")
    @ApiOperation(value = "회원 비밀번호 인증", notes = "회원 비밀번호 인증을 합니다.")
    public ResponseEntity<?> verifyPassword(
            @RequestBody @Valid VerifyPasswordRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.verifyPassword(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/find/user/info")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회합니다.")
    public ResponseEntity<?> findUserInfo(
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        UserEntry user = userService.findUser(userAdapter.getUserTb());
        AbstractSpec spec = FindUserInfoResponse.builder()
                .user(user)
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/v1/find/user/alerts")
    @ApiOperation(value = "유저 알림 리스트 조회", notes = "유저 알림 리스트 정보를 조회합니다.")
    public ResponseEntity<?> findUserAlert(
            FindUserAlertRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        Page<UserAlertEntry> alerts = userService.findUserAlerts(request, userAdapter.getUserTb());
        AbstractSpec spec = FindUserAlertResponse.builder()
                .list(alerts.getContent())
                .totalElements(alerts.getTotalElements())
                .totalPage(alerts.getTotalPages())
                .curPage(alerts.getNumber())
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/public/v1/check/overlap/id")
    @ApiOperation(value = "아이디 중복 조회", notes = "아이디 중복을 조회합니다.")
    public ResponseEntity<?> checkOverLapId(
            @RequestParam(name = "loginId") String loginId
    ) throws UserException {
        userService.checkOverLapId(loginId);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @GetMapping("/api/public/v1/check/overlap/nickName")
    @ApiOperation(value = "닉네임 중복 조회", notes = "닉네임 중복을 조회합니다.")
    public ResponseEntity<?> checkOverLapNickName(
            @RequestParam(name = "nickName") String nickName
    ) throws UserException {
        userService.checkOverLapNickName(nickName);
        return ResponseEntity.ok(new SuccessResponse());
    }
}
