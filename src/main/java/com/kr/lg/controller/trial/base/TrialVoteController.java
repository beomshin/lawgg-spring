package com.kr.lg.controller.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.VoteTRequest;
import com.kr.lg.service.trial.base.TrialVoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialVoteController {

    private final TrialVoteService trialVoteService;

    @PostMapping("/api/trial/vote")
    @ApiOperation(value = "트라이얼 투표", notes = "트라이얼 투표를 합니다.")
    public ResponseEntity<DefaultResponse> voteTrial(
            @RequestBody VoteTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialVoteService.voteTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
