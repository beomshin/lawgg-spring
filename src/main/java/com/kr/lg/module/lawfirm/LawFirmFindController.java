package com.kr.lg.module.lawfirm;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmRequest;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmsRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "LawFirmFindController", description = "로펌 조회 컨트롤러")
public class LawFirmFindController {

    private final LawFirmService lawFirmService;

    @GetMapping("/law-firms")
    @Operation(summary = "로펌 리스트 페이지 조회", description = "로펌 리스트 페이지를 조회합니다.")
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
    @Operation(summary = "로펌 상세 페이지 조회", description = "로펌 상세 페이지를 조회합니다.")
    public ModelAndView lawFirm(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Parameter(description = "로펌 아이디") @PathVariable("id") Long lawfirmId,
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
