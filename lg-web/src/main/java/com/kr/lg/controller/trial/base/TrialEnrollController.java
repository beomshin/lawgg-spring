package com.kr.lg.controller.trial.base;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.net.request.trial.base.EnrollVRequest;
import com.kr.lg.model.web.net.response.trial.base.EnrollLFTResponse;
import com.kr.lg.model.web.net.response.trial.base.EnrollUTResponse;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.model.web.net.request.trial.base.EnrollLFTRequest;
import com.kr.lg.model.web.net.request.trial.base.EnrollUTRequest;
import com.kr.lg.service.trial.base.TrialEnrollService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TrialEnrollController {

    private final TrialEnrollService trialEnrollService;

    @PostMapping("/api/trial/enroll/user")
    @ApiOperation(value = "회원 트라이얼 게시판 등록", notes = "회원 트라이얼 게시판을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollUserTrial(
            @RequestBody EnrollUTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        TrialTb trialTb =  trialEnrollService.enrollUserTrial(new TrialLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = trialTb != null ? new EnrollUTResponse(trialTb.getTrialId()) : new DefaultResponse(GlobalCode.FAIL_ENROLL_LAW_FIRM);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/trial/enroll/law-firm")
    @ApiOperation(value = "로펌 트라이얼 게시판 등록", notes = "로펌으로 트라이얼 게시판을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollLawFirmTrial(
            @RequestBody EnrollLFTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        TrialTb trialTb = trialEnrollService.enrollLawFirmTrial(new TrialLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = trialTb != null ? new EnrollLFTResponse(trialTb.getTrialId()) : new DefaultResponse(GlobalCode.FAIL_ENROLL_LAW_FIRM);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/trial/enroll/video")
    public ResponseEntity enrollVideo(
            @ModelAttribute EnrollVRequest request,
            @ApiParam(value = "회원EnrollVideoDto 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialEnrollService.enrollVideo(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok().body(new DefaultResponse());
    }
}
