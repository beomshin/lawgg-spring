package com.kr.lg.controller.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.ReportTRequest;
import com.kr.lg.service.trial.base.TrialReportService;
import io.swagger.annotations.ApiOperation;
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
public class TrialReportController {

    private final TrialReportService trialReportService;

    @PostMapping("/api/public/trial/report")
    @ApiOperation(value = "트라이얼 게시판 신고", notes = "트라이얼 게시판을 신고합니다.")
    public ResponseEntity<DefaultResponse> reportTrial(
            HttpServletRequest httpServletRequest,
            @RequestBody ReportTRequest request
    ) throws LgException {
        int result = trialReportService.reportTrial(new TrialLayer(request, ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_REPORT_BOARD);
        return ResponseEntity.ok(new DefaultResponse());
    }
}
