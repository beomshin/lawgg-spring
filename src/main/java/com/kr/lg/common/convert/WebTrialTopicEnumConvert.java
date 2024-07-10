package com.kr.lg.common.convert;

import com.kr.lg.common.enums.TrialTopicEnum;
import org.springframework.core.convert.converter.Converter;

public class WebTrialTopicEnumConvert implements Converter<Integer, TrialTopicEnum> {

    @Override
    public TrialTopicEnum convert(Integer source) {
        return TrialTopicEnum.of(source);
    }
}
