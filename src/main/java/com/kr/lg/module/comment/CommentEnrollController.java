package com.kr.lg.module.comment;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.comment.model.req.EnrollTrialCommentRequest;
import com.kr.lg.module.comment.model.req.EnrollPositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "CommentEnrollController", description = "댓글 등록 컨트롤러")
public class CommentEnrollController {

    private final CommentService commentService;

    @PostMapping("/position/comment/enroll")
    @Operation(summary = "포지션 게시판 댓글 작성하기", description = "포지션 게시판 댓글 작성합니다.")
    public ModelAndView positionCommentEnroll(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollPositionCommentRequest request,
            ModelAndView mav,
            HttpServletRequest servletRequest
    ) throws CommentException {
        if (userTb == null) {
            commentService.enrollBoardCommentNotWithLogin(request, ClientUtils.getRemoteIP(servletRequest));
        } else {
            commentService.enrollBoardCommentWithLogin(request, userTb, ClientUtils.getRemoteIP(servletRequest));
        }

        mav.setViewName("redirect:/position/" + request.getBoardId());
        return mav;
    }

    @Secured("ROLE_USER")
    @PostMapping("/trial/comment/enroll")
    @Operation(summary = "트라이얼 댓글 작성하기", description = "트라이얼 댓글 작성합니다.")
    public ModelAndView trialCommentEnroll(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollTrialCommentRequest request,
            ModelAndView mav,
            HttpServletRequest servletRequest
    ) throws CommentException {
        commentService.enrollTrialCommentWithLogin(request, userTb, ClientUtils.getRemoteIP(servletRequest));
        mav.setViewName("redirect:/trial/" + request.getTrialId());
        return mav;
    }

}
