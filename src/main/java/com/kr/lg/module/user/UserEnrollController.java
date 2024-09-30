package com.kr.lg.module.user;

import com.kr.lg.model.common.ErrorResponse;
import com.kr.lg.model.enums.GlobalResultCode;
import com.kr.lg.module.user.model.req.CheckIdRequest;
import com.kr.lg.module.user.model.req.EnrollUserRequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.req.JoinRegisterRequest;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "UserEnrollController", description = "유저 등록 컨트롤러")
public class UserEnrollController {

    private final UserService userService;

    @GetMapping("/join/agree")
    @Operation(summary = "약관 동의 페이지 조회", description = "약관동의 페이지를 조회합니다.")
    public ModelAndView joinAgree(ModelAndView mav) {
        mav.setViewName("view/member/joinAgree");
        return mav;
    }

    @RequestMapping(value = "/join/register", method = {RequestMethod.POST, RequestMethod.GET})
    @Operation(summary = "회원가입 페이지 조회", description = "회원 가입 페이지를 조회합니다.")
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

    @PostMapping("/enroll/user")
    @Operation(summary = "유저 등록", description = "유저 등록 정보를 등록합니다.")
    public ModelAndView enrollUser(
            @ModelAttribute @Valid EnrollUserRequest request,
            ModelAndView mav
    ) throws UserException {
        userService.enrollUser(request);
        mav.setViewName("view/member/joinEnd");
        return mav;
    }

    @GetMapping("/check/id")
    @Operation(summary = "아이디 중복 체크", description = "아이디 중복을 체크합니다.")
    public ResponseEntity<?> checkId(
            @ModelAttribute @Valid CheckIdRequest request
    ) {
        try {
            userService.checkId(request.getLoginId());
            return ResponseEntity.ok(new SuccessResponse());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getResultCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        }
    }

}
