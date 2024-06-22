package com.kr.lg.convert;

import com.kr.lg.enums.common.element.TrialTopicEnum;
import org.springframework.core.convert.converter.Converter;

public class WebTrialTopicEnumConvert extends LogWebConvert implements Converter<Integer, TrialTopicEnum> {

    @Override
    public TrialTopicEnum convert(Integer source) {
        return TrialTopicEnum.of(source);
    }
}
