package com.kr.lg.module.user;


import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.thirdparty.service.EmailService;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "UserFindController", description = "유저 조회 컨트롤러")
public class UserFindController {

    private final UserService userService;
    private final EmailService emailService;

    @Secured("ROLE_USER")
    @GetMapping("/my/boards")
    @Operation(summary = "나의 포지션 게시판 조회", description = "나의 포지션 게시판을 조회합니다.")
    public ModelAndView findMyBoards(
            @Valid FindMyBoardsRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws  UserException { // 미사용 기능
        Page<UserBoardEntry> boards = userService.findUserBoards(request, userTb);
        boards.stream().forEach(UserBoardEntry::additionalContent);

        mav.addObject("boards", boards);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/mypage/myBoard");
        return mav;
    }

    @Secured("ROLE_USER")
    @GetMapping("/my/alerts")
    @Operation(summary = "나의 알림 조회", description = "나의 알림을 조회합니다.")
    public ModelAndView findMyAlert(
            @Valid FindMyAlertRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws UserException {
        Page<UserAlertEntry> alerts = userService.findUserAlerts(request, userTb);
        alerts.stream().forEach(UserAlertEntry::additionalContent);

        mav.addObject("alerts", alerts);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/mypage/alerts");
        return mav;
    }

    @Secured("ROLE_USER")
    @GetMapping("/my/info")
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    public ModelAndView findUserInfo(ModelAndView mav)  {
        mav.setViewName("view/mypage/myInfo");
        return mav;
    }

    @RequestMapping(value = "/user/ids", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "회원 아이디 조회", description = "회원 아이디를 조회합니다.")
    public ModelAndView userIds(
            @ModelAttribute FindIdsRequest request,
            ModelAndView mav
    ) {
        try {
            emailService.verifyEmail(request.getTxId(), request.getCode());
            List<UserEntry> ids = userService.findIds(request.getEmail());
            mav.addObject("ids", ids);
            mav.setViewName("view/member/idSearchResult");
        } catch (Exception e) {
            log.error("", e);
            mav.setViewName("view/member/idSearch");
        }
        return mav;
    }

    @RequestMapping(value = "/user/pws", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    public ModelAndView userPws(
            @ModelAttribute FindPwsRequest request,
            ModelAndView mav
    ) {
        try {
            emailService.verifyEmail(request.getTxId(), request.getCode());
            UserTb userTb = userService.findPws(request.getEmail(), request.getLoginId());
            mav.addObject("user", userTb);
            mav.addObject("txId", request.getTxId());
            mav.addObject("code", request.getCode());
            mav.setViewName("view/member/pwSearchReset");
        } catch (Exception e) {
            log.error("", e);
            mav.setViewName("view/member/pwSearch");
        }
        return mav;
    }

    @GetMapping("/news")
    @Operation(summary = "new 소식 페이지 조회", description = "new 소식 페이지를 조회합니다.")
    public ModelAndView news(ModelAndView mav) {
        mav.setViewName("view/news/list");
        return mav;
    }


}
