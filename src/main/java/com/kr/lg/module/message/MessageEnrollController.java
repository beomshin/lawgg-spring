package com.kr.lg.module.message;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.message.model.req.SendMessageRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageEnrollController {

    private final MessageService messageService;

    @PostMapping("/api/send/message")
    public ResponseEntity<?> sendMessage(
            @RequestBody @Valid SendMessageRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws MessageException {
        messageService.sendMessage(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

}
