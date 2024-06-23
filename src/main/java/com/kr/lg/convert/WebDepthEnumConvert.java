package com.kr.lg.convert;

import com.kr.lg.enums.entity.element.DepthEnum;
import org.springframework.core.convert.converter.Converter;

public class WebDepthEnumConvert implements Converter<Integer, DepthEnum> {

    @Override
    public DepthEnum convert(Integer source) {
        return DepthEnum.of(source);
    }
}
