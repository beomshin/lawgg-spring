package com.kr.lg.controller.trial.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.web.net.request.trial.comment.EnrollCTRequest;
import com.kr.lg.service.trial.comment.TrialCommentEnrollService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialCommentEnrollController {

    private final TrialCommentEnrollService trialCommentEnrollService;

    @PostMapping("/api/trial/enroll/comment")
    @ApiOperation(value = "회원 트라이얼 게시판 댓글 등록", notes = "회원으로 트라이얼 게시판 댓글을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollCommentTrial(
            @RequestBody EnrollCTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialCommentEnrollService.enrollCommentTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
