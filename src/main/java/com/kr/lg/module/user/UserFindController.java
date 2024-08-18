package com.kr.lg.module.user;


import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.entry.UserAlertEntry;
import com.kr.lg.module.user.model.entry.UserBoardEntry;
import com.kr.lg.module.user.model.req.*;
import com.kr.lg.module.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserFindController {

    private final UserService userService;

    @Secured("ROLE_USER")
    @ApiOperation(value = "나의 포지션 게시판 조회", notes = "나의 포지션 게시판을 조회합니다.")
    @GetMapping("/my/boards")
    public ModelAndView findMyBoards(
            @Valid FindMyBoardsRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws  UserException { // 미사용 기능
        Page<UserBoardEntry> boards = userService.findUserBoards(request, userTb);
        boards.stream().forEach(UserBoardEntry::additionalContent);

        mav.addObject("boards", boards);
        mav.addObject("query", request);
        mav.addObject("maxPage", 10);

        mav.setViewName("view/mypage/myBoard");
        return mav;
    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "나의 알림 조회", notes = "나의 알림을 조회합니다.")
    @GetMapping("/my/alerts")
    public ModelAndView findMyAlert(
            @Valid FindMyAlertRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws UserException {
        Page<UserAlertEntry> alerts = userService.findUserAlerts(request, userTb);
        alerts.stream().forEach(UserAlertEntry::additionalContent);

        mav.addObject("alerts", alerts);
        mav.addObject("query", request);
        mav.addObject("maxPage", 10);

        mav.setViewName("view/mypage/alerts");
        return mav;
    }

    @Secured("ROLE_USER")
    @GetMapping("/my/info")
    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보를 조회합니다.")
    public ModelAndView findUserInfo(ModelAndView mav)  {
        mav.setViewName("view/mypage/myInfo");
        return mav;
    }

    @GetMapping("/lck")
    public ModelAndView lck(ModelAndView mav) {
        mav.setViewName("view/lck/list");
        return mav;
    }


}
