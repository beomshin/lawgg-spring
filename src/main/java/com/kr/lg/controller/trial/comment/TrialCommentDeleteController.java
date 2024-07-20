package com.kr.lg.controller.trial.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.comment.DeleteCTRequest;
import com.kr.lg.service.trial.comment.TrialCommentDeleteService;
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
public class TrialCommentDeleteController {

    private final TrialCommentDeleteService trialCommentDeleteService;

    @PostMapping("/api/trial/delete/comment")
    @ApiOperation(value = "회원 트라이얼 게시판 댓글 삭제", notes = "회원 트라이얼 게시판 댓글 삭제를합니다.")
    public ResponseEntity<DefaultResponse> deleteCommentTrial(
            @RequestBody DeleteCTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialCommentDeleteService.deleteCommentTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
