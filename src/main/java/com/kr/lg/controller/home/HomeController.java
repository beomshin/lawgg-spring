package com.kr.lg.controller.home;

import com.kr.lg.web.net.response.home.HomeMainTrialResponse;
import com.kr.lg.web.net.response.home.HomeResponse;
import com.kr.lg.service.home.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/api/public/home")
    public ResponseEntity<HomeResponse> home() throws Exception {
        HomeResponse body = homeService.findHome();
        return ResponseEntity.ok(body);
    }
    
    @GetMapping("/api/public/hot")
    public ResponseEntity<HomeMainTrialResponse> hotTrial() throws Exception {
        HomeMainTrialResponse body = homeService.findHotTrial();
        return ResponseEntity.ok(body);
    }
}
