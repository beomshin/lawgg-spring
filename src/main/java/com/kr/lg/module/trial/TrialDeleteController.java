package com.kr.lg.module.trial;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.trial.model.req.DeleteTrialRequest;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialDeleteController {

    private final TrialService trialService;

    @PostMapping("/api/v1/delete/trial")
    @ApiOperation(value = "회원 트라이얼 게시판 삭제", notes = "회원 트라이얼 게시판 삭제를 합니다.")
    public ResponseEntity<?> deleteTrial(
            @RequestBody @Valid DeleteTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws TrialException {
        trialService.deleteTrial(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

}
