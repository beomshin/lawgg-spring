package com.kr.lg.controller.message;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.MainLayer;
import com.kr.lg.service.message.MessageDeleteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageDeleteController {

    private final MessageDeleteService messageDeleteService;

    @PostMapping("/api/delete/send/message/{id}")
    @ApiOperation(value = "발신 메세지 삭제하기", notes = "발신 메세지를 삭제하기 합니다.")
    public ResponseEntity<DefaultResponse> deleteSendMessage(
            @ApiParam(value = "메세지 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        messageDeleteService.deleteSendMessage(new MainLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/delete/receive/message/{id}")
    @ApiOperation(value = "수신 메세지 삭제하기", notes = "수신 메세지를 삭제하기 합니다.")
    public ResponseEntity<DefaultResponse> deleteReceiveMessage(
            @ApiParam(value = "메세지 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        messageDeleteService.deleteReceiveMessage(new MainLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
