package com.kr.lg.model.web.net.response.home;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.model.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class HomeMainTrialResponse extends DefaultResponse {

    private Long id;
    private String title;
    private String content;
    private String altVideoUrl;
    private String playVideo;

    public HomeMainTrialResponse(TrialTb trialTb) {
        this.id = trialTb.getTrialId();
        this.title = trialTb.getTitle();
        this.content = trialTb.getContent();
        this.altVideoUrl = trialTb.getAltVideoUrl();
        this.playVideo = trialTb.getPlayVideo();
    }
}
