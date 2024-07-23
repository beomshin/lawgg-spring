package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.module.board.model.dto.BoardRecommendEventDto;
import com.kr.lg.module.board.model.req.DeleteRecommendBoardRequest;
import com.kr.lg.module.board.model.req.RecommendBoardRequest;
import com.kr.lg.module.board.model.req.ReportBoardRequest;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.module.board.model.req.UpdateBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.UpdateBoardWithLoginRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardUpdateController {

    private final BoardService boardService;
    private final ApplicationEventPublisher applicationEventPublisher;


    @PostMapping("/api/public/v1/update/board")
    @ApiOperation(value = "비로그인 포지션 게시판 수정", notes = "비로그인 포지션 게시판 수정을 합니다.")
    public ResponseEntity<?> updateBoardWithNotLogin(
            @RequestBody @Valid UpdateBoardWithNotLoginRequest request
    ) throws BoardException {
        boardService.updateBoardWithNotLogin(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/update/board")
    @ApiOperation(value = "로그인 포지션 게시판 수정", notes = "로그인 포지션 게시판 수정을 합니다.")
    public ResponseEntity<?> updateBoardWithLogin(
            @RequestBody @Valid UpdateBoardWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.updateBoardWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/public/v1/report/board")
    @ApiOperation(value = "포지션 게시판 신고", notes = "포지션 게시판을 신고합니다.")
    public ResponseEntity<?> reportBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid ReportBoardRequest request
    ) throws BoardException {
        boardService.reportBoard(request, ClientUtils.getRemoteIP(httpServletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }


    @PostMapping("/api/v1/recommend/board")
    @ApiOperation(value = "포지션 게시판 추천", notes = "포지션 게시판을 추천합니다.")
    public ResponseEntity<?> recommendBoard(
            @RequestBody @Valid RecommendBoardRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.recommendBoard(request, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new BoardRecommendEventDto(request.getId(), 1)); // 추천 수 증가
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/delete/recommend/board")
    @ApiOperation(value = "포지션 게시판 추천 삭제", notes = "포지션 게시판 추천을 삭제 합니다.")
    public ResponseEntity<?> deleteRecommendBoard(
            @RequestBody @Valid DeleteRecommendBoardRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException { // 미사용
        boardService.deleteRecommendBoard(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

}
