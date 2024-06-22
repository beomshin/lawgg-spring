package com.kr.lg.controller.message;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.MainLayer;
import com.kr.lg.model.web.net.request.message.FindRMLRequest;
import com.kr.lg.model.web.net.request.message.FindSMLRequest;
import com.kr.lg.service.message.MessageReadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageReadController {

    private final MessageReadService messageReadService;

    @GetMapping("/api/find/receive/list/message")
    public ResponseEntity<DefaultResponse> findReceiveListMessage(FindRMLRequest requestDto, @UserPrincipal UserAdapter userAdapter) throws LgException {
        DefaultResponse body = messageReadService.findReceiveListMessage(new MainLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/find/send/list/message")
    @ApiOperation(value = "발신 메세지 리스트", notes = "발신 메세지 리스트를 조회합니다.")
    public ResponseEntity<DefaultResponse> findSendListMessage(
            FindSMLRequest requestDto,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = messageReadService.findSendListMessage(new MainLayer(requestDto, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/find/receive/message/{id}")
    @ApiOperation(value = "수신 메세지 확인하기", notes = "수신 메세지를 확인합니다.")
    public ResponseEntity<DefaultResponse> findReceiveMessage(
            @ApiParam(value = "메세지 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = messageReadService.findReceiveMessage(new MainLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/find/send/message/{id}")
    @ApiOperation(value = "발신 메세지 확인하기", notes = "발신 메세지를 확인합니다.")
    public ResponseEntity<DefaultResponse> findSendMessage(
            @ApiParam(value = "메세지 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        DefaultResponse body = messageReadService.findSendMessage(new MainLayer(id, userAdapter.getUserTb()));
        return ResponseEntity.ok(body);
    }
}
