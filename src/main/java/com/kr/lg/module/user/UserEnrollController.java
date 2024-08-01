package com.kr.lg.module.user;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.user.model.req.EnrollUserRequest;
import com.kr.lg.module.user.excpetion.UserException;
import com.kr.lg.module.user.service.UserService;
import com.kr.lg.web.dto.root.SuccessResponse;
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
    public ResponseEntity<?> enrollUser(
            @RequestBody @Valid EnrollUserRequest requestDto
    ) throws UserException {
        UserTb userTb = userService.enrollUser(requestDto);
        return ResponseEntity.ok(new SuccessResponse());
    }


}
