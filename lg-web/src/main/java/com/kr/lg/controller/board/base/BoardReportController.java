package com.kr.lg.controller.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.utils.ClientUtils;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.model.web.net.request.board.base.ReportBRequest;
import com.kr.lg.service.board.base.BoardReportService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardReportController {

    private final BoardReportService boardReportService;

    @PostMapping("/api/public/board/report")
    @ApiOperation(value = "포지션 게시판 신고", notes = "포지션 게시판을 신고합니다.")
    public ResponseEntity<DefaultResponse> reportBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Validated ReportBRequest request
    ) throws LgException {
        int result = boardReportService.reportBoard(new BoardLayer(request, ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_REPORT_BOARD);
        return ResponseEntity.ok(body);
    }

}
