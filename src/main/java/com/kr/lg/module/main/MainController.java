package com.kr.lg.module.main;

import com.kr.lg.module.main.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "MainController", description = "메인 페이지 컨트롤러")
public class MainController {

    private final MainService mainService; // 메인 페이지 서비스

    @GetMapping("/")
    @Operation(summary = "메인 페이지 호출", description = "메인 페이지를 호출합니다.")
    public ModelAndView home(ModelAndView mav) {
        mav.addObject("boards", mainService.getMainPostBoards());
        mav.addObject("trials", mainService.getMainPostTrials());
        mav.addObject("hotTrial", mainService.getHotPostTrial());

        mav.setViewName("view/home");
        return mav;
    }

}
