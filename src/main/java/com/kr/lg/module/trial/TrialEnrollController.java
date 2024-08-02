package com.kr.lg.module.trial;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.module.trial.model.req.VoteTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.model.global.AbstractSpec;
import com.kr.lg.module.trial.model.req.EnrollVideoWithLoginRequest;
import com.kr.lg.module.trial.model.res.EnrollTrialWithLoginResponse;

import com.kr.lg.module.trial.model.req.EnrollTrialWithLoginRequest;
import com.kr.lg.model.global.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrialEnrollController {

    private final TrialService trialService;

    @PostMapping("/api/v1/enroll/trial")
    @ApiOperation(value = "회원 트라이얼 게시판 등록", notes = "회원 트라이얼 게시판을 등록합니다.")
    public ResponseEntity<?> enrollTrialWithLogin(
            @RequestBody @Valid EnrollTrialWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        TrialTb trialTb = trialService.enrollTrialWithLogin(request, userAdapter.getUserTb());
        AbstractSpec spec = EnrollTrialWithLoginResponse.builder()
                .id(trialTb.getTrialId())
                .build();
        return ResponseEntity.ok(spec);
    }

    @PostMapping("/api/v1/enroll/video")
    @ApiOperation(value = "회원 트라이얼 게시판 비디오 파일 등록", notes = "회원 트라이얼 게시판 비디오 파일을 등록합니다.")
    public ResponseEntity<?> enrollVideoWithLogin(
            @ModelAttribute @Valid EnrollVideoWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        TrialTb trialTb = trialService.enrollVideoWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/vote/trial")
    @ApiOperation(value = "트라이얼 투표", notes = "트라이얼 투표를 합니다.")
    public ResponseEntity<?> voteTrial(
            @RequestBody @Valid VoteTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        trialService.voteTrial(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
