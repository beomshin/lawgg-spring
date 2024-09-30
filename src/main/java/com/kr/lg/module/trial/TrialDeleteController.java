package com.kr.lg.module.trial;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.module.trial.model.req.DeleteTrialRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "TrialDeleteController", description = "트라이얼 삭제 컨트롤러")
public class TrialDeleteController {

    private final TrialService trialService;

    @Secured("ROLE_USER")
    @PostMapping("/trial/delete")
    @Operation(summary = "트라이얼 삭제하기", description = "트라이얼 삭제합니다.")
    public ModelAndView deletePosition(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeleteTrialRequest request,
            ModelAndView mav
    ) throws TrialException {
        trialService.deleteTrial(request, userTb);
        mav.setViewName("redirect:/trials");
        return mav;
    }

}
