package com.kr.lg.module.trial;


import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.common.ErrorResponse;
import com.kr.lg.model.enums.GlobalResultCode;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.ReportPositionRequest;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.req.DeleteRecommendTrialRequest;
import com.kr.lg.module.trial.model.req.RecommendTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.trial.model.req.UpdateEndTrialRequest;
import com.kr.lg.module.trial.model.req.UpdateLiveTrialRequest;

import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TrialUpdateController {

    private final TrialService trialService;

    @PostMapping("/trial/recommend")
    @ApiOperation(value = "트라이얼 게시판 추천", notes = "트라이얼 게시판을 추천합니다.")
    public ResponseEntity<?> recommendTrial(
            @RequestBody @Valid RecommendTrialRequest request,
            @AuthUser UserTb userTb
    ) {
        try {
            if (userTb == null) throw new TrialException(TrialResultCode.NOT_EXIST_USER);
            trialService.recommendTrial(request, userTb);
            return ResponseEntity.ok(new SuccessResponse());
        } catch (TrialException e) {
            HttpStatus status = e.getResultCode() == TrialResultCode.NOT_EXIST_USER ? HttpStatus.FORBIDDEN : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(new ErrorResponse(e.getResultCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        }
    }

    @PostMapping("/trial/report")
    @ApiOperation(value = "트라이얼 게시판 신고", notes = "트라이얼 게시판을 신고합니다.")
    public ResponseEntity<?> reportTrial(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid ReportTrialRequest request
    ) {
        try {
            trialService.reportTrial(request, ClientUtils.getRemoteIP(httpServletRequest));
            return ResponseEntity.ok(new SuccessResponse());
        } catch (TrialException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getResultCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        }
    }

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

}
