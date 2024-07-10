package com.kr.lg.model.common.layer;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.AcceptEnum;
import com.kr.lg.model.net.request.lawfirm.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Slf4j
@ToString
public class LawFLayer {

    private Long id;
    private Integer page;
    private Integer pageNum;
    private Integer subject;
    private String keyword;
    private UserTb userTb;
    private String title;
    private String introduction;
    private String name;
    private MultipartFile profile;
    private MultipartFile background;
    private AcceptEnum accept;
    private Long userId;

    public LawFLayer(FindALFLRequest requestDto) {
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.subject = requestDto.getSubject();
        this.keyword = requestDto.getKeyword();
    }

    public LawFLayer(Long id) {
        this.id = id;
    }

    public LawFLayer(Long id, UserTb userTb) {
        this.id = id;
        this.userTb = userTb;
    }

    public LawFLayer(UserTb userTb) {
        this.userTb = userTb;
    }

    public LawFLayer(FindAMLFLRequest requestDto, UserTb userTb) {
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.subject = requestDto.getSubject();
        this.keyword = requestDto.getKeyword();
        this.userTb = userTb;
    }

    public LawFLayer(FindUMLFLRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.subject = requestDto.getSubject();
        this.keyword = requestDto.getKeyword();
        this.userTb = userTb;
    }


    public LawFLayer(ApplyLFRequest request, UserTb userTb)  {
        this.id = request.getId();
        this.title = request.getTitle();
        this.introduction = request.getIntroduction();
        this.userTb = userTb;
    }

    public LawFLayer(DeleteLFRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }

    public LawFLayer(ActiveLFRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }

    public LawFLayer(QuitLFRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }

    public LawFLayer(DeleteLFURequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userId = requestDto.getUserId();
        this.userTb = userTb;
    }
    public LawFLayer(UpdateLFRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.introduction = requestDto.getIntroduction();
        this.profile = requestDto.getProfile();
        this.background = requestDto.getBackground();
        this.userTb = userTb;
    }

    public LawFLayer(ConfirmLFRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.accept = AcceptEnum.of(requestDto.getAccept());
        this.userTb = userTb;
    }

    public LawFLayer(EnrollLFRequest requestDto, UserTb userTb) {
        this.name = requestDto.getName();
        this.introduction = requestDto.getIntroduction();
        this.profile = requestDto.getProfile();
        this.background = requestDto.getBackground();
        this.userTb = userTb;
    }

    public LawFLayer(CancelLFURequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }
}
