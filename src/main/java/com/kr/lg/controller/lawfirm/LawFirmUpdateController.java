package com.kr.lg.controller.lawfirm;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.model.net.request.lawfirm.ActiveLFRequest;
import com.kr.lg.model.net.request.lawfirm.UpdateLFRequest;
import com.kr.lg.service.lawfirm.LawFirmUpdateService;
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
public class LawFirmUpdateController {

    private final LawFirmUpdateService lawFirmUpdateService;

    @PostMapping("/api/law-firm/active")
    @ApiOperation(value = "로펌 활성화/비활성화", notes = "로펌을 활성화/비활성화 합니다.")
    public ResponseEntity<DefaultResponse> activeLawFirm(
            @RequestBody ActiveLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmUpdateService.activeLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/law-firm/update")
    @ApiOperation(value = "로펌 수정하기", notes = "로펌을 수정합니다.")
    public ResponseEntity<DefaultResponse> updateLawFirm(
            @ModelAttribute UpdateLFRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        lawFirmUpdateService.updateLawFirm(new LawFLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
