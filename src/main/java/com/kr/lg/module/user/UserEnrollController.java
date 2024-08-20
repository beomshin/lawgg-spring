package com.kr.lg.module.user;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.user.model.req.EnrollUserRequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.req.JoinRegisterRequest;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserEnrollController {

    private final UserService userService;

    @GetMapping("/join/agree")
    public String joinAgree(

    ) {
        return "view/member/joinAgree";
    }

    @RequestMapping("/join/register")
    public ModelAndView joinRegister(
            @ModelAttribute @Valid JoinRegisterRequest request,
            ModelAndView mav
    ) {
        if (request.getAccept() == null || !request.getAccept()) { // 회원가입 미동의
            mav.setViewName("view/member/joinAgree");
        } else { // 동의
            mav.setViewName("view/member/joinRegister");
        }
        return mav;
    }

    @PostMapping("/api/public/v1/enroll/user")
    @ApiOperation(value = "유저 등록", notes = "유저 등록 정보를 등록합니다.")
    public ResponseEntity<?> enrollUser(
            @RequestBody @Valid EnrollUserRequest requestDto
    ) throws UserException {
        UserTb userTb = userService.enrollUser(requestDto);
        return ResponseEntity.ok(new SuccessResponse());
    }


}
