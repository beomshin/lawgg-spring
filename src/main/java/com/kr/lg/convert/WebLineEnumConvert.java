package com.kr.lg.convert;

import com.kr.lg.enums.entity.element.LineEnum;
import org.springframework.core.convert.converter.Converter;

public class WebLineEnumConvert implements Converter<Integer, LineEnum> {

    @Override
    public LineEnum convert(Integer source) {
        return LineEnum.of(source);
    }
}
