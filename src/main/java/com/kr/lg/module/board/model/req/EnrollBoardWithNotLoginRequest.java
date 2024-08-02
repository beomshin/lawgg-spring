package com.kr.lg.module.board.model.req;

import com.kr.lg.model.global.FileDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "비로그인 포지션 게시판 등록 요청 Body")
public class EnrollBoardWithNotLoginRequest {

    @ApiModelProperty(value = "아이디;", required = true)
    @NotBlank(message = "아이디 값이 입력되어있지않습니다.")
    private String id;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String password;

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "라인타입", required = true)
    @NotNull(message = "라인타입이 입력되어있지않습니다.")
    private Integer lineType;

    @ApiModelProperty(value = "파일")
    private List<FileDto> files;

}
