package com.kr.lg.module.board;


import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.SuccessResponse;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import com.kr.lg.module.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "BoardTestController", description = "포지션 게시판 테스트 컨트롤러")
public class BoardTestController {

    private final BoardService boardService;


    @PostMapping("/position/enroll/crawling")
    @Operation(summary = "포지션 게시판 크롤링 데이터 등록", description = "포지션 게시판 크롤링 데이터를 등록합니다.(테스트목적)")
    public ResponseEntity<?> enrollCrawling(
            @Valid @RequestBody EnrollPositionRequest request,
            HttpServletRequest servletRequest
    ) throws BoardException {
        boardService.enrollBoardWithNotLogin(request, ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }
}
