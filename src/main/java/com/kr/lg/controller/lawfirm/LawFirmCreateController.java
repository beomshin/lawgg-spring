package com.kr.lg.controller.lawfirm;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.layer.LawFLayer;
import com.kr.lg.web.net.request.lawfirm.ApplyLFRequest;
import com.kr.lg.web.net.request.lawfirm.ConfirmLFRequest;
import com.kr.lg.web.net.request.lawfirm.EnrollLFRequest;
import com.kr.lg.service.lawfirm.LawFirmCreateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LawFirmCreateController {

    private final LawFirmCreateService lawFirmCreateService;


    @PostMapping("/api/law-firm/apply")
    @ApiOperation(value = "로펌 신청하기", notes = "로펌 신청합니다.")
    public ResponseEntity<DefaultResponse> applyLawFirm(
            @RequestBody ApplyLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmCreateService.applyLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/law-firm/confirm")
    @ApiOperation(value = "로펌 신청 접수", notes = "로펌 신청을 접수 완료 합니다.")
    public ResponseEntity<DefaultResponse> confirmLawFirm(
            @RequestBody ConfirmLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boolean result = lawFirmCreateService.confirmLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result ? new DefaultResponse() : new DefaultResponse(GlobalCode.ALREADY_APPLY_LAW_FIRM);
        return ResponseEntity.ok(body);
    }


    @PostMapping("/api/law-firm/enroll")
    @ApiOperation(value = "로펌 만들기", notes = "로펌 만들기")
    public ResponseEntity<DefaultResponse> enrollLawFirm(
            @ModelAttribute EnrollLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmCreateService.enrollLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
