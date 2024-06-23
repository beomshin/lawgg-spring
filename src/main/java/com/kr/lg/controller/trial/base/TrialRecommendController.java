package com.kr.lg.controller.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.model.net.request.trial.base.DeleteRTRequest;
import com.kr.lg.model.net.request.trial.base.RecommendTRequest;
import com.kr.lg.service.trial.base.TrialRecommendService;
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
public class TrialRecommendController {

    private final TrialRecommendService trialRecommendService;

    @PostMapping("/api/trial/recommend")
    @ApiOperation(value = "트라이얼 게시판 추천", notes = "트라이얼 게시판을 추천합니다.")
    public ResponseEntity<DefaultResponse> recommendTrial(
            @RequestBody RecommendTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        trialRecommendService.recommendTrial(new TrialLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/trial/recommend/delete")
    @ApiOperation(value = "트라이얼 게시판 추천 삭제", notes = "트라이얼 게시판 추천을 삭제 합니다.")
    public ResponseEntity<DefaultResponse> deleteRecommendTrial(
            @RequestBody DeleteRTRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = trialRecommendService.deleteRecommendTrial(new TrialLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_RECOMMEND_BOARD);
        return ResponseEntity.ok(body);
    }
}
