
package com.kr.lg.module.lawfirm;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.lawfirm.model.req.QuitLawFirmRequest;
import com.kr.lg.module.lawfirm.model.req.CancelApplyLawFirmRequest;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class LawFirmDeleteController {

    private final LawFirmService lawFirmService;

    @Secured("ROLE_USER")
    @PostMapping("/law-firm/quit")
    @ApiOperation(value = "로펌 탈퇴", notes = "로펌을 탈퇴합니다.")
    public ResponseEntity<?> quitLawFirm(
            @RequestBody @Valid QuitLawFirmRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", readOnly = true) @AuthUser UserTb userTb
    ) throws LawFirmException {
        lawFirmService.quitLawFirm(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }


    @Secured("ROLE_USER")
    @PostMapping("/law-firm/cancel/apply")
    @ApiOperation(value = "로펌 신청 취소", notes = "로펌 신청을 취소합니다.")
    public ResponseEntity<?> cancelApplyLawFirm(
            @RequestBody @Valid CancelApplyLawFirmRequest request,
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb
    ) throws LawFirmException {
        lawFirmService.cancelApplyLawFirm(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }
}
