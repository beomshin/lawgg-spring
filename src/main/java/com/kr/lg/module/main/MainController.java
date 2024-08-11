package com.kr.lg.module.main;

import com.kr.lg.module.main.service.MainService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService; // 메인 페이지 서비스

    @ApiOperation(value = "메인 페이지 호출", notes = "메인 페이지를 호출합니다.")
    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("boards", mainService.getMainPostBoards());
        modelAndView.addObject("trials", mainService.getMainPostTrials());
        modelAndView.addObject("hotTrial", mainService.getHotPostTrial());

        modelAndView.setViewName("view/home");
        return modelAndView;
    }

}
