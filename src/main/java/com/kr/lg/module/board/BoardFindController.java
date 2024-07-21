package com.kr.lg.module.board;

import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import com.kr.lg.module.board.model.res.FindBoardResponse;
import com.kr.lg.module.board.model.res.FindLawFirmBoardResponse;
import com.kr.lg.module.board.model.res.FindMyBoardResponse;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.web.dto.root.AbstractSpec;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResponseEntity<?> findBoards(@Valid FindBoardRequest request) throws BoardException {
        Page<BoardEntry> boards = boardService.findBoards(request);

        AbstractSpec spec = FindBoardResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }

    @ApiOperation(value = "나의 포지션 게시판 조회", notes = "나의 포지션 게시판을 조회합니다.")
    @GetMapping("/api/v1/find/my/boards")
    public ResponseEntity<?> findMyBoards(
            @Valid FindMyBoardRequest request,
            @ApiParam(value = "회원 토큰", required = true)
            @UserPrincipal UserAdapter userAdapter
    ) throws BoardException { // 현재는 미사용
        Page<BoardEntry> boards = boardService.findUserBoards(request, userAdapter.getUserTb());

        AbstractSpec spec = FindMyBoardResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }

    @ApiOperation(value = "로펌 포지션 게시판 조회", notes = "로펌 포지션 게시판을 조회합니다.")
    @GetMapping("/api/public/v1/find/law-firm/boards")
    public ResponseEntity<?> findUserBoards(
            @Valid FindLawFirmBoardRequest request
    ) throws BoardException {
        Page<BoardEntry> boards = boardService.findLawFirmBoards(request);

        AbstractSpec spec = FindLawFirmBoardResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }

}

