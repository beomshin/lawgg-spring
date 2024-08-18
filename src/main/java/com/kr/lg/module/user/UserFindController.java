package com.kr.lg.module.user;


import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.message.model.entry.MessageEntry;
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
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.model.common.AbstractSpec;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserService userService;

    @Secured("ROLE_USER")
    @ApiOperation(value = "나의 포지션 게시판 조회", notes = "나의 포지션 게시판을 조회합니다.")
    @GetMapping("/my/boards")
    public ModelAndView findMyBoards(
            @Valid FindUserBoardsRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws  UserException { // 미사용 기능
        Page<UserBoardEntry> boards = userService.findUserBoards(request, userTb);
        boards.stream().forEach(UserBoardEntry::additionalContent);

        mav.addObject("boards", boards);
        mav.addObject("query", request);
        mav.addObject("maxPage", 10);

        mav.setViewName("view/mypage/myBoard");
        return mav;
    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "나의 알림 조회", notes = "나의 알림을 조회합니다.")
    @GetMapping("/my/alerts")
    public ModelAndView findUserAlert(
            @Valid FindUserAlertRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws UserException {
        Page<UserAlertEntry> alerts = userService.findUserAlerts(request, userTb);
        alerts.stream().forEach(UserAlertEntry::additionalContent);

        mav.addObject("alerts", alerts);
        mav.addObject("query", request);
        mav.addObject("maxPage", 10);

        mav.setViewName("view/mypage/alerts");
        return mav;
    }

    @Secured("ROLE_USER")
    @GetMapping("/my/info")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회합니다.")
    public ModelAndView findUserInfo(
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    )  {
        mav.setViewName("view/mypage/myInfo");
        return mav;
    }

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
