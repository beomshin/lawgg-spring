package com.kr.lg.controller.message;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.MainLayer;
import com.kr.lg.model.net.request.message.ReplyMRequest;
import com.kr.lg.model.net.request.message.SendMRequest;
import com.kr.lg.service.message.MessageCreateService;
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
public class MessageCreateController {

    private final MessageCreateService messageCreateService;

    @PostMapping("/api/send/message")
    public ResponseEntity<DefaultResponse> sendMessage(
            @RequestBody SendMRequest request, @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        messageCreateService.sendMessage(new MainLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/reply/message")
    @ApiOperation(value = "메세지 수신하기", notes = "메세지를 수신합니다.")
    public ResponseEntity<DefaultResponse> replyMessage(
            @RequestBody ReplyMRequest request,
            @ApiParam(value = "유저 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        messageCreateService.replyMessage(new MainLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
