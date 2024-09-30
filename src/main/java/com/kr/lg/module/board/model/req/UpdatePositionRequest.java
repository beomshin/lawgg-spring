package com.kr.lg.module.board.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "포지션 게시판 수정 Body")
public class UpdatePositionRequest {

    @Schema(description = "게시판 아이디")
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long boardId;

    @Schema(description = "패스워드")
    private String password;

    @Schema(description = "제목")
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @Schema(description = "내용")
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @Schema(description = "추가 파일")
    private List<MultipartFile> files;


}
