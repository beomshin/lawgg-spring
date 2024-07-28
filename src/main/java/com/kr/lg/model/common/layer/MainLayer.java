package com.kr.lg.model.common.layer;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.message.model.req.FindRMLRequest;
import com.kr.lg.module.message.model.req.FindSMLRequest;
import com.kr.lg.module.message.model.req.ReplyMRequest;
import com.kr.lg.module.message.model.req.SendMRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Slf4j
public class MainLayer {


    private Long id;
    private UserTb userTb;
    private Integer page;
    private Integer pageNum;
    private Integer subject;
    private String keyword;
    private String receiver;
    private UserTb sender;
    private String title;
    private String content;

    public MainLayer(FindRMLRequest requestDto, UserTb userTb) {
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.subject = requestDto.getSubject();
        this.keyword = requestDto.getKeyword();
        this.userTb = userTb;
    }

    public MainLayer(FindSMLRequest requestDto, UserTb userTb) {
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.subject = requestDto.getSubject();
        this.keyword = requestDto.getKeyword();
        this.userTb = userTb;
    }

    public MainLayer(SendMRequest request, UserTb userTb) {
        this.receiver = request.getReceiver();
        this.sender = userTb;
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public MainLayer(ReplyMRequest request, UserTb userTb) {
        this.id = request.getId();
        this.sender = userTb;
        this.title = request.getTitle();
        this.content = request.getContent();
    }


    public MainLayer(Long id, UserTb userTb) {
        this.id = id;
        this.userTb = userTb;
    }

}
