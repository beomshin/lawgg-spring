package com.kr.lg.module.board;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.res.FindBoardResponse;
import com.kr.lg.module.board.service.BoardService2;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardFindController2 {

    private final BoardService2 boardService2;

    @ApiOperation(value = "포지션 게시판 조회", notes = "포지션 게시판을 조회합니다.")
    @GetMapping("/api/public/v1/find/boards")
    public ResponseEntity<?> findBoards(@Valid FindBoardRequest request) {
        Page<BoardEntry> boards = boardService2.findBoards(request);
        return ResponseEntity.ok().body(new FindBoardResponse(boards));
    }
}
