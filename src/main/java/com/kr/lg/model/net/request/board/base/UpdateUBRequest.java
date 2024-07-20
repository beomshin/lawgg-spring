package com.kr.lg.model.net.request.board.base;

import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "회원 포지션 게시판 수정 요청 바디")
public class UpdateUBRequest implements RootRequest { // UpdateUserBoardRequestDto

    @ApiModelProperty(value = "게시판 아이디", required = true, example = "boardId")
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true, example = "password")
    private String password;

    @ApiModelProperty(value = "제목", required = true, example = "title")
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true, example = "content")
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "추가 파일", required = false)
    private List<GlobalFile> addFiles;


}
