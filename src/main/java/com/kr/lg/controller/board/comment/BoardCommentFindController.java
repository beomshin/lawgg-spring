package com.kr.lg.controller.board.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.enums.DepthEnum;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.comment.FindACCBRequest;
import com.kr.lg.model.net.request.board.comment.FindAPCBRequest;
import com.kr.lg.model.net.request.board.comment.FindUCCBRequest;
import com.kr.lg.model.net.request.board.comment.FindUPCBRequest;
import com.kr.lg.service.board.comment.BoardCommentFindService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardCommentFindController {

    private final BoardCommentFindService boardCommentFindService;

    @GetMapping("/api/public/board/find/anonymous/all/comment/{id}")
    @ApiOperation(value = "익명 포지션 게시판 댓글 전체 조회", notes = "익명 포지션 게시판 댓글 전체를 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousAllCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findAnonymousAllCommentBoard(new BoardLayer(id));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/board/find/user/all/comment/{id}")
    @ApiOperation(value = "회원 포지션 게시판 댓글 전체 조회", notes = "회원 포지션 게시판 댓글 전체를 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserAllCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findUserAllCommentBoard(new BoardLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/board/find/anonymous/parent/comment/{id}")
    @ApiOperation(value = "익명 포지션 게시판 댓글 부모 조회", notes = "익명 포지션 게시판 댓글 부모를 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousParentCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            FindAPCBRequest requestDto
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findAnonymousParentCommentBoard(new BoardLayer(id, requestDto, DepthEnum.PARENT_COMMENT));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/board/find/user/parent/comment/{id}")
    @ApiOperation(value = "회원 포지션 게시판 댓글 부모 조회", notes = "회원 포지션 게시판 댓글 부모를 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserParentCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter,
            FindUPCBRequest requestDto
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findUserParentCommentBoard(new BoardLayer(id, userAdapter.getUserTb(), requestDto, DepthEnum.PARENT_COMMENT));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/board/find/anonymous/children/comment/{id}")
    @ApiOperation(value = "익명 포지션 게시판 댓글 자식 조회", notes = "익명 포지션 게시판 댓글 자식를 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousChildrenCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            FindACCBRequest requestDto
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findAnonymousChildrenCommentBoard(new BoardLayer(id, requestDto, DepthEnum.CHILDREN_COMMENT));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/board/find/user/children/comment/{id}")
    @ApiOperation(value = "회원 포지션 게시판 댓글 자식 조회", notes = "회원 포지션 게시판 댓글 자식를 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserChildrenCommentBoard(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter,
            FindUCCBRequest requestDto
    ) throws LgException {
        DefaultResponse body = boardCommentFindService.findUserChildrenCommentBoard(new BoardLayer(id, userAdapter.getUserTb(), requestDto, DepthEnum.CHILDREN_COMMENT));
        return ResponseEntity.ok(body);
    }
}
