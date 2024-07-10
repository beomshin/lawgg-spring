package com.kr.lg.controller.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.LoginUTRequest;
import com.kr.lg.service.trial.base.TrialAuthService;
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
public class TrialAuthController {

    private TrialAuthService trialAuthService;

    @PostMapping("/api/trial/login/user")
    @ApiOperation(value = "회원 트라이얼 게시판 로그인", notes = "회원 트라이얼 게시판 로그인을 합니다.")
    public ResponseEntity<DefaultResponse> loginUserTrial(
            @RequestBody LoginUTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialAuthService.loginUserTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse()); 
    }
}
