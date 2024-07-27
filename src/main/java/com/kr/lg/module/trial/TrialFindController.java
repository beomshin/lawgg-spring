package com.kr.lg.module.trial;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.module.trial.model.req.FindATLRequest;
import com.kr.lg.module.trial.model.req.FindLFTLRequest;
import com.kr.lg.module.trial.service.TrialFindService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final TrialFindService trialFindService;

    @GetMapping("/api/public/v1/find/trials")
    @ApiOperation(value = "트라이얼 게시판 조회", notes = "트라이얼 게시판을 조회합니다.")
    public ResponseEntity<?> findTrials(
            @Valid FindATLRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialFindService.findAllListTrial(new TrialLayer(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/v1/find/law-firm/trials")
    @ApiOperation(value = "로펌 트라이얼 게시판 조회", notes = "로펌 트라이얼 게시판을 조회합니다.")
    public ResponseEntity<?> findLawFirmTrials(
            @Valid FindLFTLRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialFindService.findLawFirmListTrial(new TrialLayer(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/v1/find/trial/{id}")
    @ApiOperation(value = "익명 트라이얼 게시판 상세 조회", notes = "익명 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<?> findTrialWithNotLogin(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body = trialFindService.findAnonymousDetailTrial(new TrialLayer(id, ClientUtils.getRemoteIP(request)));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/v1/find/trial/{id}")
    @ApiOperation(value = "회원 트라이얼 게시판 로그인 상세 조회", notes = "회원 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<?> findTrialWithLogin(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = trialFindService.findUserDetailTrial(new TrialLayer(id, ClientUtils.getRemoteIP(request), userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

}
