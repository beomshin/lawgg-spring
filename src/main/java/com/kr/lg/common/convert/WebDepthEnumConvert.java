package com.kr.lg.common.convert;

import com.kr.lg.common.enums.DepthEnum;
import org.springframework.core.convert.converter.Converter;

public class WebDepthEnumConvert implements Converter<Integer, DepthEnum> {

    @Override
    public DepthEnum convert(Integer source) {
        return DepthEnum.of(source);
    }
}
