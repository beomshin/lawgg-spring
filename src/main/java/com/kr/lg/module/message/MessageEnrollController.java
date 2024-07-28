package com.kr.lg.module.message;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.MainLayer;
import com.kr.lg.module.message.model.req.ReplyMRequest;
import com.kr.lg.module.message.model.req.SendMRequest;
import com.kr.lg.module.message.service.MessageCreateService;
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
public class MessageEnrollController {

    private final MessageCreateService messageCreateService;

    @PostMapping("/api/send/message")
    public ResponseEntity<DefaultResponse> sendMessage(
            @RequestBody SendMRequest request, @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        messageCreateService.sendMessage(new MainLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
