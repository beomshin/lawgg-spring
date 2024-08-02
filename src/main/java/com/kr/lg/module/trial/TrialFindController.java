package com.kr.lg.module.trial;

import com.kr.lg.module.trial.model.event.TrialCountEvent;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.res.FindTrialWithNotLoginResponse;
import com.kr.lg.module.trial.model.res.FindTrialsResponse;
import com.kr.lg.module.trial.model.res.FindLawFirmTrialsResponse;
import com.kr.lg.module.trial.model.res.FindTrialWithLoginResponse;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.model.common.AbstractSpec;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.module.trial.model.req.FindTrialsRequest;
import com.kr.lg.module.trial.model.req.FindLawFirmTrialsRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialFindController {

    private final TrialService trialService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/api/public/v1/find/trials")
    @ApiOperation(value = "트라이얼 게시판 조회", notes = "트라이얼 게시판을 조회합니다.")
    public ResponseEntity<?> findTrials(
            @Valid FindTrialsRequest request
    ) throws TrialException {
        Page<TrialEntry> trials = trialService.findTrials(request);
        AbstractSpec spec = FindTrialsResponse.builder()
                .list(trials.getContent())
                .totalElements(trials.getTotalElements())
                .totalPage(trials.getTotalPages())
                .curPage(trials.getNumber())
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/public/v1/find/law-firm/trials")
    @ApiOperation(value = "로펌 트라이얼 게시판 조회", notes = "로펌 트라이얼 게시판을 조회합니다.")
    public ResponseEntity<?> findLawFirmTrials(
            @Valid FindLawFirmTrialsRequest request
    ) throws TrialException {
        Page<TrialEntry> trials = trialService.findLawFirmTrials(request);
        AbstractSpec spec = FindLawFirmTrialsResponse.builder()
                .list(trials.getContent())
                .totalElements(trials.getTotalElements())
                .totalPage(trials.getTotalPages())
                .curPage(trials.getNumber())
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/public/v1/find/trial/{id}")
    @ApiOperation(value = "비로그인 트라이얼 게시판 상세 조회", notes = "비로그인 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<?> findTrialWithNotLogin(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id
    ) throws TrialException {
        TrialEntry trial = trialService.findTrialWithNotLogin(id);
        applicationEventPublisher.publishEvent(new TrialCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가
        AbstractSpec spec = FindTrialWithNotLoginResponse.builder().trial(trial).build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/v1/find/trial/{id}")
    @ApiOperation(value = "로그인 트라이얼 게시판 로그인 상세 조회", notes = "로그인 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<?> findTrialWithLogin(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws  TrialException {
        TrialEntry trial = trialService.findTrialWithLogin(id, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new TrialCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가
        AbstractSpec spec = FindTrialWithLoginResponse.builder().trial(trial).build();
        return ResponseEntity.ok(spec);
    }

}
