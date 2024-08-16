package com.kr.lg.module.trial;


import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.common.ErrorResponse;
import com.kr.lg.model.enums.GlobalResultCode;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.req.RecommendTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.module.trial.model.req.EndTrialRequest;
import com.kr.lg.module.trial.model.req.StartTrialRequest;

import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
            @ApiParam(value = "로그인 세션 유저 정보")@AuthUser UserTb userTb
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

    @Secured("ROLE_JUDGE")
    @PostMapping("/trial/start")
    @ApiOperation(value = "트라이얼 게시판 재판 시작", notes = "트라이얼 게시판 재판을 시작합니다.")
    public ResponseEntity<?> startTrial(
            @RequestBody @Valid StartTrialRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb
    ) throws TrialException {
        trialService.trialStartLive(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @Secured("ROLE_JUDGE")
    @PostMapping("/trial/end")
    @ApiOperation(value = "트라이얼 게시판 재판 종료", notes = "트라이얼 게시판 재판을 종료합니다.")
    public ResponseEntity<?> endTrial(
            @RequestBody @Valid EndTrialRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb
    ) throws TrialException {
        trialService.trialEndLive(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }

}
