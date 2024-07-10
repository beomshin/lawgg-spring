package com.kr.lg.module.main.model.res;

import com.kr.lg.module.main.model.dto.MainText;
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

    List<MainText> boards; // 포지션 게시판 리스트

    List<MainText> trials; // 트라이얼 리스트

    MainText hotTrial; // HOT 트라이얼

    @Override
    protected void setError(GlobalCode code) {
        this.resultCode = code.getCode();
        this.resultMsg = code.getMsg();
        this.success = GlobalCode.isSuccess(code);
    }
}
