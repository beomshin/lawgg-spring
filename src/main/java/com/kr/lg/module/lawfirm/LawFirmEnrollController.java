package com.kr.lg.module.lawfirm;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmService;

import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.model.common.SuccessResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LawFirmEnrollController {

    private final LawFirmService lawFirmService;

    @Secured("ROLE_USER")
    @PostMapping("/law-firm/apply")
//    @ApiOperation(value = "로펌 신청하기", notes = "로펌 신청합니다.")
    public ResponseEntity<?> applyLawFirm(
            @RequestBody @Valid ApplyLawFirmRequest request,
//            @ApiParam(value = "로그인 세션 유저 정보", required = true)
            @AuthUser UserTb userTb
    ) throws LawFirmException {
        lawFirmService.applyLawFirm(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }

}
