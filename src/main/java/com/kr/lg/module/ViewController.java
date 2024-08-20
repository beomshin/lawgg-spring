package com.kr.lg.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ViewController {

    @GetMapping("/join/end")
    public String joinEnd() {
        return "view/member/joinEnd";
    }

    @GetMapping("/pw/search")
    public String pwSearch() {
        return "view/member/pwSearch";
    }

    @GetMapping("/pw/reset")
    public String pwRest() {
        return "view/member/pwSearchReset";
    }

    @GetMapping("/id/search")
    public String idSearch() {
        return "view/member/idSearch";
    }

    @GetMapping("/id/result")
    public String idResult() {
        return "view/member/idSearchResult";
    }

}
