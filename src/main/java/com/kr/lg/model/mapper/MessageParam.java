package com.kr.lg.model.mapper;

import lombok.*;
import org.springframework.data.domain.Pageable;
import com.kr.lg.model.mapper.MapperParam;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageParam<T extends MapperParam> {

    private T data; // mapper data 파라미터

    private Pageable pageable; //  페이징 파라미터
}
