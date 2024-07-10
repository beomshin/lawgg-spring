package com.kr.lg.controller.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.FindATLRequest;
import com.kr.lg.model.net.request.trial.base.FindLFTLRequest;
import com.kr.lg.model.net.request.trial.base.FindUTLRequest;
import com.kr.lg.service.trial.base.TrialFindService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrialFindController {

    private final TrialFindService trialFindService;

    @GetMapping("/api/public/trial/find/all/list")
    @ApiOperation(value = "트라이얼 게시판 조회", notes = "트라이얼 게시판을 조회합니다.")
    public ResponseEntity<DefaultResponse> findAllListTrial(
            FindATLRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialFindService.findAllListTrial(new TrialLayer(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/trial/find/user/list")
    @ApiOperation(value = "회원 트라이얼 게시판 조회", notes = "회원 트라이얼 게시판을 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserListTrial(
            FindUTLRequest requestDto,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = trialFindService.findUserListTrial(new TrialLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/law-firm/trial/find/all/list")
    @ApiOperation(value = "로펌 트라이얼 게시판 조회", notes = "로펌 트라이얼 게시판을 조회합니다.")
    public ResponseEntity<DefaultResponse> findLawFirmListTrial(
            FindLFTLRequest requestDto
    ) throws LgException {
        DefaultResponse body = trialFindService.findLawFirmListTrial(new TrialLayer(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/trial/find/anonymous/detail/{id}")
    @ApiOperation(value = "익명 트라이얼 게시판 상세 조회", notes = "익명 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousDetailTrial(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body = trialFindService.findAnonymousDetailTrial(new TrialLayer(id, ClientUtils.getRemoteIP(request)));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/trial/find/user/detail/{id}")
    @ApiOperation(value = "회원 트라이얼 게시판 로그인 상세 조회", notes = "회원 트라이얼 게시판을 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserDetailTrial(
            HttpServletRequest request,
            @ApiParam(value = "트라이얼 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = trialFindService.findUserDetailTrial(new TrialLayer(id, ClientUtils.getRemoteIP(request), userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

}
