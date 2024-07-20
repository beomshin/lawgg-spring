package com.kr.lg.module.board;

import com.kr.lg.module.board.service.BoardService2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardFindController2 {

    private final BoardService2 boardService2;

    @GetMapping("/api/public/v1/find/boards")
    public ResponseEntity<?> findBoards() {
        return ResponseEntity.ok().body(null);
    }
}
