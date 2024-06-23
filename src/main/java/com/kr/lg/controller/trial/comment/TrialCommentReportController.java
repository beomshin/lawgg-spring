package com.kr.lg.controller.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.utils.ClientUtils;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.web.net.request.trial.comment.ReportCTRequest;
import com.kr.lg.service.trial.comment.TrialCommentReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialCommentReportController {

    private final TrialCommentReportService trialCommentReportService;

    @PostMapping("/api/trial/report/comment")
    @ApiOperation(value = "트라이얼 게시판 댓글 신고", notes = "트라이얼 게시판을 댓글 신고합니다.")
    public ResponseEntity<DefaultResponse> reportCommentTrial(
            HttpServletRequest httpServletRequest,
            @RequestBody ReportCTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = trialCommentReportService.reportCommentTrial(new TrialLayer(request, ClientUtils.getRemoteIP(httpServletRequest), userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_REPORT_BOARD_COMMENT);
        return ResponseEntity.ok(body);
    }

}
