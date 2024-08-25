package com.kr.lg.module.board;


import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.SuccessResponse;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import com.kr.lg.module.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardTestController {

    private final BoardService boardService;

    @PostMapping("/position/enroll/crawling")
    public ResponseEntity<?> enrollCrawling(
            @Valid @RequestBody EnrollPositionRequest request,
            HttpServletRequest servletRequest
    ) throws BoardException {
        boardService.enrollBoardWithNotLogin(request, ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }
}
