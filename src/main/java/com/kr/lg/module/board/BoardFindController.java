package com.kr.lg.module.board;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.res.FindBoardResponse;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.web.dto.root.AbstractSpec;
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
public class BoardFindController {

    private final BoardService boardService;

    @ApiOperation(value = "포지션 게시판 조회", notes = "포지션 게시판을 조회합니다.")
    @GetMapping("/api/public/v1/find/boards")
    public ResponseEntity<?> findBoards(@Valid FindBoardRequest request) {
        Page<BoardEntry> boards = boardService.findBoards(request);

        AbstractSpec spec = FindBoardResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }
}
