package com.kr.lg.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @GetMapping( "/error")
    public String redirectRoot(){
        return "index.html";
    }

}
