package com.kr.lg.model.web.net.request.board.base;

import com.kr.lg.model.web.common.global.GlobalFile;
import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "익명 포지션 게시판 수정 요청 바디")
public class UpdateABRequest implements RootRequest { // UpdateAnonymousBoardRequestDto

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotNull(message = "패스워드가 입력되어있지않습니다.")
    private String password;

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "추가 파일")
    private List<GlobalFile> addFiles;


}
