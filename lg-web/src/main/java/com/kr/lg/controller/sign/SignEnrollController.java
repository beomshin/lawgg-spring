package com.kr.lg.controller.sign;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.SignLayer;
import com.kr.lg.model.web.net.request.sign.SignURequest;
import com.kr.lg.service.sign.SignEnrollService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignEnrollController {

    private final SignEnrollService signEnrollService;

    @PostMapping("/api/public/sign/user")
    @ApiOperation(value = "유저 등록", notes = "유저 등록 정보를 등록합니다.")
    public ResponseEntity<DefaultResponse> userSign(
            @RequestBody @Valid SignURequest requestDto
    ) throws Exception {
        signEnrollService.userSign(new SignLayer(requestDto));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
