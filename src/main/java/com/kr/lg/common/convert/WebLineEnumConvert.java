package com.kr.lg.common.convert;

import com.kr.lg.common.enums.entity.element.LineEnum;
import org.springframework.core.convert.converter.Converter;

public class WebLineEnumConvert implements Converter<Integer, LineEnum> {

    @Override
    public LineEnum convert(Integer source) {
        return LineEnum.of(source);
    }
}
