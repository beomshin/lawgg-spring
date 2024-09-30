package com.kr.lg.module.trial.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 트라이얼 게시판 등록 요청 Body")
public class EnrollTrialRequest {

    @Schema(description = "제목")
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @Schema(description = "원고")
    @NotNull(message = "원고인이 입력되어있지않습니다.")
    private String plaintiff;

    @Schema(description = "피고")
    @NotNull(message = "피고인이 입력되어있지않습니다.")
    private String defendant;

    @Schema(description = "소제목")
    @NotNull(message = "소제목이 입력되어있지않습니다.")
    private String subheading;

    @Schema(description = "원고의견")
    @NotNull(message = "원고의견이 입력되어있지않습니다.")
    private String plaintiffOpinion;

    @Schema(description = "피고의견")
    @NotNull(message = "피고의견이 입력되어있지않습니다.")
    private String defendantOpinion;

    @Schema(description = "내용")
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @Schema(description = "비디오")
    @NotNull(message = "비디오가 입력되어있지않습니다.")
    private MultipartFile video;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Schema(description = "로그인 트라이얼 비디오,리플레이 등록 요청 Body")
    public static class EnrollVideoWithLoginRequest {

        @NotNull
        @Schema(description = "비디오")
        private MultipartFile playVideo;

        @Schema(description = "리플레이")
        private MultipartFile replay;

        @Schema(description = "트라이얼 아이디")
        private Long id;

    }
}
