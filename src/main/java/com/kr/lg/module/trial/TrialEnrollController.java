package com.kr.lg.module.trial;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.trial.model.req.VoteTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;

import com.kr.lg.module.trial.model.req.EnrollTrialRequest;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrialEnrollController {

    private final TrialService trialService;

    @PostMapping("/trial/enroll")
    @ApiOperation(value = "트라이얼 게시판 작성하기", notes = "트라이얼 게시판 작성합니다.")
    public ModelAndView enrollTrial(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollTrialRequest request,
            ModelAndView modelAndView
    ) throws TrialException {
        TrialTb trialTb = trialService.enrollTrialWithLogin(request, userTb);
        modelAndView.setViewName("redirect:/trials");
        return modelAndView;
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
