package com.kr.lg.module.user;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.common.layer.SignLayer;
import com.kr.lg.model.net.request.sign.SignURequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.root.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserEnrollController {

    private final UserService userService;

    @PostMapping("/api/public/v1/enroll/user")
    @ApiOperation(value = "유저 등록", notes = "유저 등록 정보를 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollUser(
            @RequestBody @Valid SignURequest requestDto
    ) throws UserException {
        UserTb userTb = userService.enrollUser(requestDto);
        return ResponseEntity.ok(new DefaultResponse());
    }
}
