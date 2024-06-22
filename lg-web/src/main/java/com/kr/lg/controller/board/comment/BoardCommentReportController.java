package com.kr.lg.controller.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.utils.ClientUtils;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.model.web.net.request.board.comment.ReportCBRequest;
import com.kr.lg.service.board.comment.BoardCommentReportService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardCommentReportController {

    private final BoardCommentReportService boardCommentReportService;

    @PostMapping("/api/public/board/report/comment")
    @ApiOperation(value = "포지션 게시판 댓글 신고", notes = "포지션 게시판을 댓글 신고합니다.")
    public ResponseEntity<DefaultResponse> reportCommentBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody ReportCBRequest request
    ) throws LgException {
        int result = boardCommentReportService.reportCommentBoard(new BoardLayer(request, ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_REPORT_BOARD_COMMENT);
        return ResponseEntity.ok(body);
    }

}
