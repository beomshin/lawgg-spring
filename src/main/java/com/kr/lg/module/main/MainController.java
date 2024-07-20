package com.kr.lg.module.main;

import com.kr.lg.module.main.model.res.MainResponse;
import com.kr.lg.module.main.service.MainService;
import com.kr.lg.web.dto.root.AbstractSpec;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService; // 메인 페이지 서비스

    @ApiOperation(value = "메인 페이지 정보 조회", notes = "메인 페이지 정보를 조회합니다.")
    @GetMapping("/api/public/v1/find/main")
    public ResponseEntity<?> findMain() {
        AbstractSpec spec = MainResponse.builder()
                .boards(mainService.getMainPostBoards()) // 포지션 게시판 리스트
                .trials(mainService.getMainPostTrials()) // 트라이얼 리스트
                .hotTrial(mainService.getHotPostTrial()) // HOT 트라이얼
                .build();

        return ResponseEntity.ok(spec);
    }
}
