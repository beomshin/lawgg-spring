package com.kr.lg.module.message.model.res;

import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindReceiveMessagesResponse extends AbstractSpec {

    List<?> messages;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

}
