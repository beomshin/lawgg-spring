package com.kr.lg.controller.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.comment.UpdateCTRequest;
import com.kr.lg.service.trial.comment.TrialCommentUpdateService;
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
public class TrialCommentUpdateController {

    private final TrialCommentUpdateService trialCommentUpdateService;

    @PostMapping("/api/trial/update/comment")
    @ApiOperation(value = "회원 트라이얼 게시판 댓글 수정", notes = "회원 트라이얼 게시판 댓글 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateCommentTrial(
            @RequestBody UpdateCTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = trialCommentUpdateService.updateCommentTrial(new TrialLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_UPDATE_BOARD_COMMENT);
        return ResponseEntity.ok(body);
    }
}
