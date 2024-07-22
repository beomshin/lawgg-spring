package com.kr.lg.controller.lawfirm;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.model.net.request.lawfirm.FindALFLRequest;
import com.kr.lg.model.net.request.lawfirm.FindAMLFLRequest;
import com.kr.lg.model.net.request.lawfirm.FindUMLFLRequest;
import com.kr.lg.service.lawfirm.LawFirmReadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LawFirmReadController {


    private final LawFirmReadService lawFirmReadService;

    @GetMapping("/api/public/law-firm/list")
    @ApiOperation(value = "로펌 리스트 조회", notes = "로펌 리스트를 조회합니다.")
    public ResponseEntity<DefaultResponse> findAllLawFirmList(
            FindALFLRequest requestDto
    ) throws LgException {
        DefaultResponse body =  lawFirmReadService.findAllLawFirmList(new LawFLayer(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/anonymous/law-firm/{id}")
    @ApiOperation(value = "로펌 유저 비회원 상세 조회", notes = "로펌 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findAnonymousLawFirmDetail(
            @ApiParam(value = "로펌 아이디", required = true) @PathVariable("id") Long id
    ) throws LgException {
        DefaultResponse body =  lawFirmReadService.findAnonymousLawFirmDetail(new LawFLayer(id));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/user/law-firm/{id}")
    @ApiOperation(value = "로펌 정보 유저 상세 조회", notes = "로펌 상세 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserLawFirmDetail(
            @ApiParam(value = "로펌 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body =  lawFirmReadService.findUserLawFirmDetail(new LawFLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/law-firm/find/my")
    @ApiOperation(value = "내 로펌 정보 조회하기", notes = "내 로펌 정보를 조회합니다.")
    public ResponseEntity<DefaultResponse> findMyLawFirm(
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body =  lawFirmReadService.findMyLawFirm(new LawFLayer(userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/law-firm/find/apply/list/my")
    @ApiOperation(value = "로펌 지원자 리스트 조화", notes = "로펌 지원자 리스트를 조회합니다.")
    public ResponseEntity<DefaultResponse> findApplyListMyLawFirm(
            FindAMLFLRequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body =  lawFirmReadService.findApplyListMyLawFirm(new LawFLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/law-firm/find/user/list/my")
    @ApiOperation(value = "로펌 유저 리스트 조회", notes = "로펌 유저 리스트를 조회합니다.")
    public ResponseEntity<DefaultResponse> findUserListMyLawFirm(
            FindUMLFLRequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = lawFirmReadService.findUserListMyLawFirm(new LawFLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

}
