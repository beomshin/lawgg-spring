
package com.kr.lg.module.lawfirm;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.module.lawfirm.model.req.QuitLawFirmRequest;
import com.kr.lg.module.lawfirm.model.req.CancelApplyLawFirmRequest;
import com.kr.lg.model.common.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "LawFirmDeleteController", description = "로펌 삭제 컨트롤러")
public class LawFirmDeleteController {

    private final LawFirmService lawFirmService;

    @Secured("ROLE_USER")
    @PostMapping("/law-firm/quit")
    @Operation(summary = "로펌 탈퇴", description = "로펌을 탈퇴합니다.")
    public ResponseEntity<?> quitLawFirm(
            @RequestBody @Valid QuitLawFirmRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws LawFirmException {
        lawFirmService.quitLawFirm(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }


    @Secured("ROLE_USER")
    @PostMapping("/law-firm/cancel/apply")
    @Operation(summary = "로펌 신청 취소", description = "로펌 신청을 취소합니다.")
    public ResponseEntity<?> cancelApplyLawFirm(
            @RequestBody @Valid CancelApplyLawFirmRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws LawFirmException {
        lawFirmService.cancelApplyLawFirm(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }
}
