package com.kr.lg.module.lawfirm;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmRequest;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmsRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LawFirmFindController {

    private final LawFirmService lawFirmService;

    @GetMapping("/law-firms")
//    @ApiOperation(value = "로펌 리스트 ?이지 호출", notes = "로펌 리스트 페이지를 호출합니다.")
    public ModelAndView lawFirms(
            @Valid @ModelAttribute FindLawFirmsRequest request,
            ModelAndView mav
    ) throws LawFirmException {
        Page<LawFirmEntry> lawFirms = lawFirmService.findLawFirms(request);
        lawFirms.getContent().forEach(LawFirmEntry::additionalContent); // 필요 정보 재세팅

        mav.addObject("lawFirms", lawFirms);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/lawfirm/index");
        return mav;
    }

    @GetMapping("/law-firm/{id}")
    public ModelAndView lawFirm(
//            @ApiParam(value = "로그인 세션 유저 정보")
            @AuthUser UserTb userTb,
//            @ApiParam(value = "로펌 아이디", required = true)
            @PathVariable("id") Long lawfirmId,
            @Valid @ModelAttribute FindLawFirmRequest request,
            ModelAndView mav
    ) throws LawFirmException {
        LawFirmEntry lawFirm = userTb == null ? lawFirmService.findLawFirmWithNotLogin(lawfirmId) : lawFirmService.findLawFirmWithLogin(lawfirmId, userTb);
        Page<LawFirmBoardEntry> trials = lawFirmService.findLawFirmBoard(request, lawfirmId);
        trials.getContent().forEach(LawFirmBoardEntry::additionalContent); // 필요 정보 재세팅

        mav.addObject("lawFirm", lawFirm);
        mav.addObject("trials", trials);
        mav.addObject("query", request);
        mav.addObject("lawfirmId", lawfirmId);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/lawfirm/list");
        return mav;
    }

}
