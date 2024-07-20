package com.kr.lg.web.dto.mapper.board;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;
import org.springframework.data.domain.Pageable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardParam<T extends MapperParam> {

    private T data;

    private Pageable pageable;
}
