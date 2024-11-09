package com.kr.lg.module.trial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.kafka.KafkaService;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.kafka.ArticleLawggBoard;
import com.kr.lg.model.kafka.ArticleLawggTrial;
import com.kr.lg.module.trial.model.req.VoteTrialRequest;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;

import com.kr.lg.module.trial.model.req.EnrollTrialRequest;
import com.kr.lg.model.common.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "TrialEnrollController", description = "트라이얼 등록 컨트롤러")
public class TrialEnrollController {

    private final TrialService trialService;
    private final KafkaService kafkaService;

    @Value("${kafka.topic.article.name}")
    private String topic;

    @Secured("ROLE_USER")
    @PostMapping("/trial/enroll")
    @Operation(summary = "트라이얼 게시판 작성하기", description = "트라이얼 게시판 작성합니다.")
    public ModelAndView enrollTrial(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollTrialRequest request,
            ModelAndView mav,
            HttpServletRequest servletRequest
    ) throws TrialException, JsonProcessingException {
        TrialTb trialTb = trialService.enrollTrialWithLogin(request, userTb);

        kafkaService.sendMessage(topic, new ArticleLawggTrial(userTb, request, ClientUtils.getRemoteIP(servletRequest), trialTb)); // kafka 메세지 발행 (게시판 등록)
        mav.setViewName("redirect:/trials");
        return mav;
    }

    @Secured("ROLE_USER")
    @PostMapping("/trial/vote")
    @Operation(summary = "트라이얼 투표", description = "트라이얼 투표를 합니다.")
    public ResponseEntity<?> voteTrial(
            @RequestBody @Valid VoteTrialRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws TrialException {
        trialService.voteTrial(request, userTb);
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
