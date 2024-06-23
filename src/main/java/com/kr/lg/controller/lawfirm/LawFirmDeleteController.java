
package com.kr.lg.controller.lawfirm;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.layer.LawFLayer;
import com.kr.lg.web.net.request.lawfirm.DeleteLFRequest;
import com.kr.lg.web.net.request.lawfirm.QuitLFRequest;
import com.kr.lg.web.net.request.lawfirm.CancelLFURequest;
import com.kr.lg.web.net.request.lawfirm.DeleteLFURequest;
import com.kr.lg.service.lawfirm.LawFirmDeleteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LawFirmDeleteController {

    private final LawFirmDeleteService lawFirmDeleteService;

    @PostMapping("/api/law-firm/delete")
    @ApiOperation(value = "로펌 삭제하기", notes = "로펌을 삭제합니다.")
    public ResponseEntity<DefaultResponse> deleteLawFirm(
            @RequestBody DeleteLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmDeleteService.deleteLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }


    @PostMapping("/api/law-firm/quit")
    @ApiOperation(value = "로펌 탈퇴하기", notes = "로펌을 탈퇴합니다.")
    public ResponseEntity<DefaultResponse> quitLawFirm(
            @RequestBody QuitLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result =  lawFirmDeleteService.quitLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_LAW_FIRM_DELETE);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/law-firm/user/delete")
    @ApiOperation(value = "로펌 탈퇴시키기", notes = "회원을 로펌에 탈퇴시킵니다.")
    public ResponseEntity<DefaultResponse> userDeleteLawFirm(
            @RequestBody DeleteLFURequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmDeleteService.userDeleteLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }


    @PostMapping("/api/law-firm/user/cancel")
    @ApiOperation(value = "로펌 취소하기", notes = "회원을 로펌에 탈퇴시킵니다.")
    public ResponseEntity<DefaultResponse> userCancelLawFirm(
            @RequestBody CancelLFURequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = lawFirmDeleteService.userCancelLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_CANCEL_APPLY);
        return ResponseEntity.ok(body);
    }
}
