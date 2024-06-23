package com.kr.lg.controller.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.entity.element.DepthEnum;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.comment.FindACCTRequest;
import com.kr.lg.model.net.request.trial.comment.FindAPCTrialRequest;
import com.kr.lg.model.net.request.trial.comment.FindUCCTRequest;
import com.kr.lg.model.net.request.trial.comment.FindUPCTRequest;
import com.kr.lg.service.trial.comment.TrialCommentFindService;
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
public class TrialCommentFindController {

    private final TrialCommentFindService trialCommentFindService;

    @GetMapping("/api/public/trial/find/anonymous/all/comment/{id}")
    @ApiOperation(value = "익명 트라이얼 게시판 전체 댓글 조회", notes = "익명 트라이얼 게시판 전체 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousAllCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findAnonymousAllCommentTrial(new TrialLayer(id));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/trial/find/user/all/comment/{id}")
    @ApiOperation(value = "회원 트라이얼 게시판 전체 댓글 조회", notes = "회원 트라이얼 게시판 전체 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserAllCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findUserAllCommentTrial(new TrialLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/trial/find/anonymous/parent/comment/{id}")
    @ApiOperation(value = "익명 트라이얼 게시판 부모 댓글 조회", notes = "익명 트라이얼 게시판 부모 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousParentCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            FindAPCTrialRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findAnonymousParentCommentTrial(new TrialLayer(id, requestDto, DepthEnum.PARENT_COMMENT));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/trial/find/user/parent/comment/{id}")
    @ApiOperation(value = "회원 트라이얼 게시판 부모 댓글 조회", notes = "회원 트라이얼 게시판 부모 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserParentCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter,
            FindUPCTRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findUserParentCommentTrial(new TrialLayer(id, userAdapter.getUserTb(), requestDto, DepthEnum.PARENT_COMMENT));
        return ResponseEntity.ok(body);
    }


    @GetMapping("/api/public/trial/find/anonym/children/comment/{id}")
    @ApiOperation(value = "익명 트라이얼 게시판 자식 댓글 조회", notes = "익명 트라이얼 게시판 자식 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousChildrenCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            FindACCTRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findAnonymousChildrenCommentTrial(new TrialLayer(id, requestDto, DepthEnum.CHILDREN_COMMENT));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/trial/find/user/children/comment/{id}")
    @ApiOperation(value = "회원 트라이얼 게시판 자식 댓글 조회", notes = "회원 트라이얼 게시판 자식 댓글을 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserChildrenCommentTrial(
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter,
            FindUCCTRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialCommentFindService.findUserChildrenCommentTrial(new TrialLayer(id, userAdapter.getUserTb(), requestDto, DepthEnum.CHILDREN_COMMENT));
        return ResponseEntity.ok(body);
    }
}
