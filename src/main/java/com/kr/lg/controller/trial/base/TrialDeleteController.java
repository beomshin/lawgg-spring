package com.kr.lg.controller.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.web.net.request.trial.base.DeleteUTRequest;
import com.kr.lg.service.trial.base.TrialDeleteService;
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
public class TrialDeleteController {

    private final TrialDeleteService trialDeleteService;

    @PostMapping("/api/trial/delete/user")
    @ApiOperation(value = "회원 트라이얼 게시판 삭제", notes = "회원 트라이얼 게시판 삭제를 합니다.")
    public ResponseEntity<DefaultResponse> deleteUserTrial(
            @RequestBody DeleteUTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialDeleteService.deleteUserTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
