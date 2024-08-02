package com.kr.lg.module.lawfirm;

import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.model.global.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class LawFirmEnrollController {

    private final LawFirmService lawFirmService;

    @PostMapping("/api/v1/apply/law-firm")
    @ApiOperation(value = "로펌 신청하기", notes = "로펌 신청합니다.")
    public ResponseEntity<?> applyLawFirm(
            @RequestBody @Valid ApplyLawFirmRequest request,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws LawFirmException {
        lawFirmService.applyLawFirm(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

}
