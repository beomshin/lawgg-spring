package com.kr.lg.controller.board.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.base.DeleteRBRequest;
import com.kr.lg.model.net.request.board.base.RecommendBRequest;
import com.kr.lg.service.board.base.BoardRecommendService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardRecommendController {

    private final BoardRecommendService boardRecommendService;

    @PostMapping("/api/board/recommend")
    @ApiOperation(value = "포지션 게시판 추천", notes = "포지션 게시판을 추천합니다.")
    public ResponseEntity<DefaultResponse> recommendBoard(
            @RequestBody @Validated RecommendBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boardRecommendService.recommendBoard(new BoardLayer(request.getId(), userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/board/delete/recommend")
    @ApiOperation(value = "포지션 게시판 추천 삭제", notes = "포지션 게시판 추천을 삭제 합니다.")
    public ResponseEntity<DefaultResponse> deleteRecommendBoard(
            @RequestBody @Validated DeleteRBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = boardRecommendService.deleteRecommendBoard(new BoardLayer(request.getId(), userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_RECOMMEND_BOARD);
        return ResponseEntity.ok(body);
    }
}
