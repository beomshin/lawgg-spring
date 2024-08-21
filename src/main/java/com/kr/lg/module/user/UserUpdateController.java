package com.kr.lg.module.user;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.model.req.ResetPwRequest;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.user.model.req.UpdateUserAlertRequest;
import com.kr.lg.module.user.model.req.UpdateUserPasswordRequest;
import com.kr.lg.module.user.model.req.UpdateUserProfileRequest;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserUpdateController {

    private final UserService userService;

    @Secured("ROLE_USER")
    @PostMapping("/my/update/password")
    @ApiOperation(value = "회원 비밀번호 업데이트", notes = "회원 비밀번호 업데이트합니다.")
    public ModelAndView updatePassword(
            @ModelAttribute @Valid UpdateUserPasswordRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws UserException {
        userService.updateUserPassword(request, userTb);
        mav.setViewName("redirect:/my/info");
        return mav;
    }

    @Secured("ROLE_USER")
    @PostMapping("/my/update/profile")
    @ApiOperation(value = "회원 프로필 업데이트", notes = "회원 프로필 업데이트합니다.")
    public ResponseEntity<?> updateUserProfile(
            @ModelAttribute @Valid UpdateUserProfileRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws  UserException {
        userService.updateUserProfile(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/my/reset/password")
    @ApiOperation(value = "회원 비밀번호 업데이트", notes = "회원 비밀번호 업데이트합니다.")
    public ModelAndView resetUserPassword(
            @ModelAttribute @Valid ResetPwRequest request,
            ModelAndView mav
    ) {
        try {
            userService.resetPassword(request);
            mav.setViewName("redirect:/logout");
        } catch (Exception e) {
            mav.setViewName("view/member/pwSearch");
        }
        return mav;
    }


    @PostMapping("/api/v1/update/read/user/alerts")
    @ApiOperation(value = "회원 알림 리스트 업데이트", notes = "회원 알림 리스트를 업데이트합니다.")
    public ResponseEntity<?> updateUserAlertAll(
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.updateReadUserAlerts(userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/update/user/alert")
    public ResponseEntity<?> updateUserAlert(
            @RequestBody @Valid UpdateUserAlertRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws UserException {
        userService.updateReadUserAlert(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }
}
