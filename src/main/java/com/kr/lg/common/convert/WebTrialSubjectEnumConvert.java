package com.kr.lg.common.convert;

import com.kr.lg.common.enums.common.element.TrialSubjectEnum;
import org.springframework.core.convert.converter.Converter;

public class WebTrialSubjectEnumConvert implements Converter<Integer, TrialSubjectEnum> {

    @Override
    public TrialSubjectEnum convert(Integer source) {
        return TrialSubjectEnum.of(source);
    }
}
