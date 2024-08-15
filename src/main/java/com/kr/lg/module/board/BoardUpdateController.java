package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.common.ErrorResponse;
import com.kr.lg.model.enums.GlobalResultCode;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.model.req.RecommendPositionRequest;
import com.kr.lg.module.board.model.req.ReportPositionRequest;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.module.board.model.req.UpdatePositionRequest;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardUpdateController {

    private final BoardService boardService;

    @PostMapping("/position/recommend")
    @ApiOperation(value = "포지션 게시판 추천", notes = "포지션 게시판을 추천합니다.")
    public ResponseEntity<?> recommendBoard(
            @RequestBody @Valid RecommendPositionRequest request,
            @AuthUser UserTb userTb
    ) {
        try {
            if (userTb == null) throw new BoardException(BoardResultCode.NOT_EXIST_USER);
            boardService.recommendBoard(request, userTb);
            return ResponseEntity.ok(new SuccessResponse());
        } catch (BoardException e) {
            HttpStatus status = e.getResultCode() == BoardResultCode.NOT_EXIST_USER ? HttpStatus.FORBIDDEN : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(new ErrorResponse(e.getResultCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        }
    }

    @PostMapping("/position/report")
    @ApiOperation(value = "포지션 게시판 신고", notes = "포지션 게시판을 신고합니다.")
    public ResponseEntity<?> reportBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid ReportPositionRequest request
    ) {
        try {
            boardService.reportBoard(request, ClientUtils.getRemoteIP(httpServletRequest));
            return ResponseEntity.ok(new SuccessResponse());
        } catch (BoardException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getResultCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/position/update")
    @ApiOperation(value = "포지션 게시판 수정", notes = "포지션 게시판을 수정합니다.")
    public ModelAndView updateBoard(
            @ModelAttribute @Valid UpdatePositionRequest request,
            @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws BoardException {
        boardService.updateBoardWithLogin(request, userTb);
        mav.setViewName("redirect:/position/" + request.getBoardId());
        return mav;
    }


}
