package com.kr.lg.controller.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.net.request.trial.base.UpdateETRequest;
import com.kr.lg.model.net.request.trial.base.UpdateLTRequest;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.UpdateTRequest;
import com.kr.lg.service.trial.base.TrialUpdateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialUpdateController {

    private final TrialUpdateService trialUpdateService;

    @PostMapping("/api/trial/update")
    @ApiOperation(value = "트라이얼 게시판 수정", notes = "트라이얼 게시판 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateTrial(
            @ModelAttribute UpdateTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialUpdateService.updateTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }


    @PostMapping("/api/trial/update/live")
    public ResponseEntity<DefaultResponse> updateLiveTrial(
            @RequestBody @Validated UpdateLTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = new DefaultResponse();
        trialUpdateService.updateLiveTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }


    @PostMapping("/api/trial/update/end")
    public ResponseEntity<DefaultResponse> updateEndTrial(
            @RequestBody @Validated UpdateETRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = new DefaultResponse();
        trialUpdateService.updateEndTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }
}
