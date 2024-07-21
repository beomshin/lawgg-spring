package com.kr.lg.controller.board.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.base.OldBoardFindService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OldBoardFindController {
    private final OldBoardFindService oldBoardFindService;

//    @GetMapping("/api/public/board/find/all/list")
//    @ApiOperation(value = "포지션 게시판 전체 조회", notes = "포지션 게시판 전체를 조회합니다.")
//    public ResponseEntity<DefaultResponse> findAllListBoard(
//            @Validated FindBoardRequest requestDto
//    ) throws LgException {
//        DefaultResponse body = boardFindService.findAllListBoard(new BoardLayer(requestDto));
//        return ResponseEntity.ok(body);
//    }

//    @GetMapping("/api/board/find/user/list")
//    @ApiOperation(value = "회원 포지션 게시판 조회", notes = "회원 포지션 게시판을 조회합니다.")
//    public ResponseEntity<DefaultResponse> findUserListBoard(
//            @Validated FindUserBoardRequest requestDto,
//            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
//    ) throws LgException {
//        DefaultResponse body = boardFindService.findUserListBoard(new BoardLayer(requestDto, userAdapter.getUserTb()));
//        return ResponseEntity.ok(body);
//    }

//    @GetMapping("/api/public/board/find/law-firm/list")
//    @ApiOperation(value = "회원 로펌 포지션 게시판 조회", notes = "회원 러팜 포지션 게시판을 조회합니다.")
//    public ResponseEntity<DefaultResponse> findLawFirmListBoard(
//            @Validated FindLawFirmBoardRequest requestDto
//    ) throws LgException {
//        DefaultResponse body = oldBoardFindService.findLawFirmListBoard(new BoardLayer(requestDto));
//        return ResponseEntity.ok(body);
//    }

    @GetMapping("/api/public/board/find/anonymous/detail/{id}")
    @ApiOperation(value = "익명 포지션 게시판 상세 조회", notes = "익명 포지션 게시판을 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousDetailBoard(
            HttpServletRequest request,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body = oldBoardFindService.findAnonymousDetailBoard(new BoardLayer(id, ClientUtils.getRemoteIP(request)));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/board/find/user/detail/{id}")
    @ApiOperation(value = "회원 포지션 게시판 로그인 상세 조회", notes = "회원 포지션 게시판을 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserDetailBoard(
            HttpServletRequest request,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = oldBoardFindService.findUserDetailBoard(new BoardLayer(id, ClientUtils.getRemoteIP(request), userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

}
