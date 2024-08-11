package com.kr.lg.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ViewController {

    @GetMapping("/board")
    public String board() {
        return "view/position/view";
    }

    @GetMapping("/board/write")
    public String boardWrite() {
        return "view/position/write";
    }


    @GetMapping("/trials")
    public String trials() {
        return "view/trial/list";
    }

    @GetMapping("/trial")
    public String trial() {
        return "view/trial/view";
    }

    @GetMapping("/trial/write")
    public String trialWrite() {
        return "view/trial/write";
    }

    @GetMapping("/lck")
    public String lck() {
        return "view/lck/list";
    }

    @GetMapping("/law-firms")
    public String lawFirms() {
        return "view/lawfirm/index";
    }

    @GetMapping("/law-firm")
    public String lawFirm() {
        return "view/lawfirm/list";
    }

    @GetMapping("/law-firm/{id}")
    public String lawFirmPost() {
        return "view/lawfirm/view";
    }

    @GetMapping("/law-firm/write")
    public String lawFirmWrite() {
        return "view/lawfirm/write";
    }

    @GetMapping("/join/agree")
    public String joinAgree() {
        return "view/member/joinAgree";
    }

    @GetMapping("/join/register")
    public String joinRegister() {
        return "view/member/joinRegister";
    }

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

    @GetMapping("/my/message")
    public String myMessage() {
        return "view/mypage/message";
    }

    @GetMapping("/my/boards")
    public String myBoards() {
        return "view/mypage/myBoard";
    }

    @GetMapping("/my/info")
    public String myInfo() {
        return "view/mypage/myInfo";
    }
}
