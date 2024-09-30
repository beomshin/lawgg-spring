package com.kr.lg.module.trial;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.trial.model.event.TrialCountEvent;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.module.trial.model.req.FindTrialsRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TrialFindController {

    private final TrialService trialService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/trials")
//    @ApiOperation(value = "트라이얼 게시판 페이지 호출", notes = "트라이얼 게시판 페이지를 호출합니다.")
    public ModelAndView trials(
            @Valid @ModelAttribute FindTrialsRequest request,
            ModelAndView mav
    ) throws TrialException {
        Page<TrialEntry> trials = trialService.findTrials(request);
        trials.getContent().forEach(TrialEntry::additionalContent); // 필요 정보 재세팅

        mav.addObject("trials", trials);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/trial/list");
        return mav;
    }

//    @ApiOperation(value = "트라이얼 게시판 상세 페이지 호출", notes = "트라이얼 게시판 상세 페이지를 호출합니다.")
    @GetMapping("/trial/{id}")
    public ModelAndView trial(
//            @ApiParam(value = "로그인 세션 유저 정보")
            @AuthUser UserTb userTb,
//            @ApiParam(value = "트라이얼 아이디", required = true)
            @PathVariable("id") Long id,
            ModelAndView mav,
            HttpServletRequest request
    ) throws TrialException {
        TrialEntry trialEntry = userTb == null ? trialService.findTrialWithNotLogin(id) : trialService.findTrialWithLogin(id, userTb);
        trialEntry.additionalContent2();
        mav.addObject("trial", trialEntry);
        applicationEventPublisher.publishEvent(new TrialCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가

        mav.setViewName("view/trial/view");
        return mav;
    }

    @Secured("ROLE_USER")
    @GetMapping("/trial/write")
//    @ApiOperation(value = "트라이얼 게시판 작성 페이지 호출", notes = "트라이얼 게시판 작성 페이지를 호출합니다.")
    public ModelAndView trialWrite(ModelAndView mav) {
        mav.setViewName("view/trial/write");
        return mav;
    }

}
