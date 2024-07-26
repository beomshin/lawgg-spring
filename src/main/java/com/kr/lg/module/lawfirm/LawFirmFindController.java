package com.kr.lg.module.lawfirm;

import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.res.FindLawFirmWithNotLoginResponse;
import com.kr.lg.module.lawfirm.model.res.FindLawFirmsResponse;
import com.kr.lg.module.lawfirm.model.res.FindLawFirmWithLoginResponse;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.lawfirm.model.req.FindLawFirmsRequest;
import com.kr.lg.web.dto.root.AbstractSpec;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LawFirmFindController {

    private final LawFirmService lawFirmService;

    @GetMapping("/api/public/v1/find/law-firms")
    @ApiOperation(value = "로펌 리스트 조회", notes = "로펌 리스트를 조회합니다.")
    public ResponseEntity<?> findLawFirms(
            @Valid FindLawFirmsRequest request
    ) throws LawFirmException {
        Page<LawFirmEntry> lawFirms = lawFirmService.findLawFirms(request);

        AbstractSpec spec = FindLawFirmsResponse.builder()
                .lawFirms(lawFirms.getContent())
                .totalElements(lawFirms.getTotalElements())
                .totalPage(lawFirms.getTotalPages())
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/public/v1/find/law-firm/{id}")
    @ApiOperation(value = "비로그인 로펌 상세 조회", notes = "비로그인 로펌 상세 조회합니다.")
    public ResponseEntity<?> findLawFirmWithNotLogin(
            @ApiParam(value = "로펌 아이디", required = true) @PathVariable("id") Long id
    ) throws LawFirmException {
        LawFirmEntry entry = lawFirmService.findLawFirmWithNotLogin(id);
        AbstractSpec spec = FindLawFirmWithNotLoginResponse.builder()
                .lawFirm(entry)
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/v1/find/law-firm/{id}")
    @ApiOperation(value = "로그인 로펌 상세 조회", notes = "로그인 로펌 상세 조회합니다.")
    public ResponseEntity<?> findLawFirmWithLogin(
            @ApiParam(value = "로펌 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws LawFirmException {
        LawFirmEntry entry = lawFirmService.findLawFirmWithLogin(id, userAdapter.getUserTb());
        AbstractSpec spec = FindLawFirmWithLoginResponse.builder()
                .lawFirm(entry)
                .build();
        return ResponseEntity.ok(spec);
    }



}
