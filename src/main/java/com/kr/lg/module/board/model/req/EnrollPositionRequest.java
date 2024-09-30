package com.kr.lg.module.board.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "비로그인 포지션 게시판 등록 요청 Body")
public class EnrollPositionRequest {

    @Schema(description = "아이디;")
    @NotBlank(message = "아이디 값이 입력되어있지않습니다.")
    private String id;

    @Schema(description = "패스워드")
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String password;

    @Schema(description = "제목")
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @Schema(description = "내용")
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @Schema(description = "라인타입")
    @NotNull(message = "라인타입이 입력되어있지않습니다.")
    private Integer lineType;

    @Schema(description = "파일")
    private List<MultipartFile> files;

}
