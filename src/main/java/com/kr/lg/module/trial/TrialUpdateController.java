package com.kr.lg.module.trial;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.model.common.listener.AlertTLEvent;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.trial.model.req.UpdateEndTrialRequest;
import com.kr.lg.module.trial.model.req.UpdateLiveTrialRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialUpdateController {

    private final TrialService trialService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/api/v1/live/start/trial")
    public ResponseEntity<?> updateLiveTrial(
            @RequestBody @Valid UpdateLiveTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        TrialTb trialTb = trialService.trialStartLive(request, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new AlertTLEvent(trialTb));
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
