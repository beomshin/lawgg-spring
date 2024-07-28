package com.kr.lg.module.trial;


import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.module.board.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.req.DeleteRecommendTrialRequest;
import com.kr.lg.module.trial.model.req.RecommendTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.trial.model.req.UpdateEndTrialRequest;
import com.kr.lg.module.trial.model.req.UpdateLiveTrialRequest;

import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialUpdateController {

    private final TrialService trialService;

    @PostMapping("/api/v1/live/start/trial")
    public ResponseEntity<?> updateLiveTrial(
            @RequestBody @Valid UpdateLiveTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        trialService.trialStartLive(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/live/end/trial")
    public ResponseEntity<?> updateEndTrial(
            @RequestBody @Valid UpdateEndTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        trialService.trialEndLive(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/recommend/trial")
    @ApiOperation(value = "트라이얼 게시판 추천", notes = "트라이얼 게시판을 추천합니다.")
    public ResponseEntity<?> recommendTrial(
            @RequestBody @Valid RecommendTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        trialService.recommendTrial(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/delete/recommend/trial")
    @ApiOperation(value = "트라이얼 게시판 추천 삭제", notes = "트라이얼 게시판 추천을 삭제 합니다.")
    public ResponseEntity<?> deleteRecommendTrial(
            @RequestBody @Valid DeleteRecommendTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException { // 미사용
        trialService.deleteRecommendTrial(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/public/v1/report/trial")
    @ApiOperation(value = "트라이얼 게시판 신고", notes = "트라이얼 게시판을 신고합니다.")
    public ResponseEntity<?> reportTrial(
            HttpServletRequest servletRequest,
            @RequestBody @Valid ReportTrialRequest request
    ) throws TrialException {
        trialService.reportTrial(request, ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }

}
