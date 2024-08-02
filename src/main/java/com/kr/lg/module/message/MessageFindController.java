package com.kr.lg.module.message;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.model.res.FindReceiveMessagesResponse;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.model.global.AbstractSpec;
import com.kr.lg.module.message.model.req.FindReceiveMessagesRequest;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageFindController {

    private final MessageService messageService;

    @GetMapping("/api/v1/find/receive/messages")
    public ResponseEntity<?> findReceiveMessages(
            @Valid FindReceiveMessagesRequest requestDto,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws MessageException {
        Page<MessageEntry> messages = messageService.findReceiveMessages(requestDto, userAdapter.getUserTb());
        AbstractSpec spec = FindReceiveMessagesResponse.builder()
                .messages(messages.getContent())
                .totalElements(messages.getTotalElements())
                .totalPage(messages.getTotalPages())
                .curPage(messages.getNumber())
                .build();
        return ResponseEntity.ok(spec);
    }

}
