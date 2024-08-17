package com.kr.lg.module.trial.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로그인 트라이얼 게시판 등록 요청 Body")
public class EnrollTrialRequest {

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "원고", required = true)
    @NotNull(message = "원고인이 입력되어있지않습니다.")
    private String plaintiff;

    @ApiModelProperty(value = "피고", required = true)
    @NotNull(message = "피고인이 입력되어있지않습니다.")
    private String defendant;

    @ApiModelProperty(value = "소제목", required = true)
    @NotNull(message = "소제목이 입력되어있지않습니다.")
    private String subheading;

    @ApiModelProperty(value = "원고의견", required = true)
    @NotNull(message = "원고의견이 입력되어있지않습니다.")
    private String plaintiffOpinion;

    @ApiModelProperty(value = "피고의견", required = true)
    @NotNull(message = "피고의견이 입력되어있지않습니다.")
    private String defendantOpinion;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "비디오")
    @NotNull(message = "비디오가 입력되어있지않습니다.")
    private MultipartFile video;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @ApiModel(value = "로그인 트라이얼 비디오,리플레이 등록 요청 Body")
    public static class EnrollVideoWithLoginRequest {

        @NotNull
        @ApiModelProperty(value = "비디오")
        private MultipartFile playVideo;

        @ApiModelProperty(value = "리플레이")
        private MultipartFile replay;

        @ApiModelProperty(value = "트라이얼 아이디")
        private Long id;

    }
}