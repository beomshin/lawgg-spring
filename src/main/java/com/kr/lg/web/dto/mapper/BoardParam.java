package com.kr.lg.web.dto.mapper;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;
import org.springframework.data.domain.Pageable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardParam<T extends MapperParam> {

    private T data; // mapper data 파라미터

    private Pageable pageable; //  페이징 파라미터
}
