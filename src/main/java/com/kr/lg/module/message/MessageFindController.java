package com.kr.lg.module.message;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.module.message.model.req.FindMessagesRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "MessageFindController", description = "메세지 등록 컨트롤러")
public class MessageFindController {

    private final MessageService messageService;

    @Secured("ROLE_USER")
    @GetMapping("/my/messages")
    @Operation(summary = "유저 메세지 리스트 조회", description = "유저 메세지 리스트 정보를 조회합니다.")
    public ModelAndView findMessages(
            @Valid @ModelAttribute FindMessagesRequest request,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws MessageException { // 페이지는 궁하였으나 메시지 기능 미적용
        Page<MessageEntry> messages = messageService.findReceiveMessages(request, userTb);
        messages.stream().forEach(MessageEntry::additionalContent);

        mav.addObject("messages", messages);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/mypage/message");
        return mav;
    }

}
