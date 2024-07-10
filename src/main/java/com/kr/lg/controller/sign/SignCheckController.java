package com.kr.lg.controller.sign;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.SignLayer;
import com.kr.lg.service.sign.SignCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignCheckController {

    private final SignCheckService signCheckService;


    @GetMapping("/api/public/sign/check/id")
    public ResponseEntity<DefaultResponse> checkIdSign(@RequestParam(name = "loginId") String loginId) throws LgException {
        signCheckService.checkIdSign(new SignLayer(loginId));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @GetMapping("/api/public/sign/check/nickName")
    public ResponseEntity<DefaultResponse> checkNickNameSign(@RequestParam(name = "nickName") String nickName) throws LgException {
        signCheckService.checkNickNameSign(SignLayer.builder().nickName(nickName).build());
        return ResponseEntity.ok(new DefaultResponse());
    }
}
