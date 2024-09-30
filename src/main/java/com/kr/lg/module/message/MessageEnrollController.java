package com.kr.lg.module.message;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.module.message.model.req.SendMessageRequest;
import com.kr.lg.model.common.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "MessageEnrollController", description = "메세지 등록 컨트롤러")
public class MessageEnrollController {

    private final MessageService messageService;

    @Secured("ROLE_USER")
    @PostMapping("/my/send/message")
    @Operation(summary = "유저 메세지 등록 조회", description = "유저 메세지를 등록합니다.")
    public ResponseEntity<?> sendMessage(
            @RequestBody @Valid SendMessageRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws MessageException { // 페이지는 궁하였으나 메시지 기능 미적용
        messageService.sendMessage(request, userTb);
        return ResponseEntity.ok(new SuccessResponse());
    }

}
