package com.kr.lg.module;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@Tag(name = "ViewController", description = "뷰 컨트롤러")
public class ViewController {

    @GetMapping("/pw/search")
    @Operation(summary = "비밀번호 찾기 페이지 조회", description = "비밀번호 찾기 페이지 조회합니다.")
    public String pwSearch() {
        return "view/member/pwSearch";
    }

    @GetMapping("/pw/reset")
    @Operation(summary = "비밀번호 재설정 페이지 조회", description = "비밀번호 재설정 페이지 조회합니다.")
    public String pwRest() {
        return "view/member/pwSearchReset";
    }

    @GetMapping("/id/search")
    @Operation(summary = "아이디 찾기 페이지 조회", description = "아이디 찾기 페이지 조회합니다.")
    public String idSearch() {
        return "view/member/idSearch";
    }

    @GetMapping("/id/result")
    @Operation(summary = "아이디 찾기 결과 페이지 조회", description = "아이디 찾기 결과 페이지 조회합니다.")
    public String idResult() {
        return "view/member/idSearchResult";
    }

}
