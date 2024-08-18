package com.kr.lg.module.message;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.module.message.model.req.FindReceiveMessagesRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageFindController {

    private final MessageService messageService;

    @Secured("ROLE_USER")
    @GetMapping("/my/messages")
    @ApiOperation(value = "유저 메세지 리스트 조회", notes = "유저 메세지 리스트 정보를 조회합니다.")
    public ModelAndView findUserAlert(
            @Valid FindReceiveMessagesRequest request,
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            ModelAndView mav
    ) throws MessageException { // 페이지는 궁하였으나 메시지 기능 미적용
        Page<MessageEntry> messages = messageService.findReceiveMessages(request, userTb);
        messages.stream().forEach(MessageEntry::additionalContent);

        mav.addObject("user", userTb);
        mav.addObject("messages", messages);
        mav.addObject("query", request);
        mav.addObject("maxPage", 10);

        mav.setViewName("view/mypage/message");

        return mav;
    }

}
