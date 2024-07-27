package com.kr.lg.module.trial;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.model.common.listener.AlertVideoEvent;
import com.kr.lg.module.trial.model.dto.TrialCreateCount;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.module.trial.model.req.EnrollVideoWithLoginRequest;
import com.kr.lg.module.trial.model.res.EnrollTrialWithLoginResponse;

import com.kr.lg.module.trial.model.req.EnrollTrialWithLoginRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrialEnrollController {

    private final TrialService trialService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/api/v1/enroll/trial")
    @ApiOperation(value = "회원 트라이얼 게시판 등록", notes = "회원 트라이얼 게시판을 등록합니다.")
    public ResponseEntity<?> enrollTrialWithLogin(
            @RequestBody EnrollTrialWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        TrialTb trialTb = trialService.enrollTrialWithLogin(request, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new TrialCreateCount(userAdapter.getUserTb(), 1));
        AbstractSpec spec = EnrollTrialWithLoginResponse.builder()
                .id(trialTb.getTrialId())
                .build();
        return ResponseEntity.ok(spec);
    }

    @PostMapping("/api/v1/enroll/video")
    public ResponseEntity<?> enrollVideoWithLogin(
            @ModelAttribute EnrollVideoWithLoginRequest request,
            @ApiParam(value = "회원EnrollVideoDto 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        TrialTb trialTb = trialService.enrollVideoWithLogin(request, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new AlertVideoEvent(trialTb));
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
