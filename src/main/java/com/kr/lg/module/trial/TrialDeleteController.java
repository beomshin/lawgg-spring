package com.kr.lg.module.trial;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.module.trial.model.req.DeleteTrialRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TrialDeleteController {

    private final TrialService trialService;

    @Secured("ROLE_USER")
    @PostMapping("/trial/delete")
    @ApiOperation(value = "트라이얼 게시판 삭제하기", notes = "트라이얼 게시판 삭제합니다.")
    public ModelAndView deletePosition(
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeleteTrialRequest request,
            ModelAndView mav
    ) throws TrialException {
        trialService.deleteTrial(request, userTb);
        mav.setViewName("redirect:/trials");
        return mav;
    }

}
