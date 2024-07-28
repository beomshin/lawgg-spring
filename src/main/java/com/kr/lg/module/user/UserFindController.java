package com.kr.lg.module.user;

import com.kr.lg.exception.LgException;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.model.res.FindUserBoardsResponse;
import com.kr.lg.module.user.model.res.FindUserIdResponse;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.module.user.service.UserFindService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserService userService;
    private final UserFindService userFindService;

    @PostMapping("/api/public/v1/find/user/id")
    @ApiOperation(value = "회원 아이디 조회", notes = "회원 아이디 정보를 조회합니다.")
    public ResponseEntity<?> findUserId(
            @RequestBody @Valid FindUserIdRequest request
    ) throws LgException, UserException {
        List<UserEntry> ids = userService.findUserId(request);
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

    @GetMapping("/api/user/find/alert")
    public ResponseEntity<DefaultResponse> findUserAlert(
            FindUARequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = userFindService.findUserAlert(new UserLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }
}
