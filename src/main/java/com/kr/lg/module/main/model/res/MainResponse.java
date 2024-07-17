package com.kr.lg.module.main.model.res;

import com.kr.lg.module.main.model.dto.MainPost;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.root.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MainResponse extends AbstractSpec {

    List<MainPost> boards; // 포지션 게시판 리스트

    List<MainPost> trials; // 트라이얼 리스트

    MainPost hotTrial; // HOT 트라이얼

}
