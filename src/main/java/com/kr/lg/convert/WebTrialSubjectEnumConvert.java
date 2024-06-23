package com.kr.lg.convert;

import com.kr.lg.enums.common.element.TrialSubjectEnum;
import org.springframework.core.convert.converter.Converter;

public class WebTrialSubjectEnumConvert extends LogWebConvert implements Converter<Integer, TrialSubjectEnum> {

    @Override
    public TrialSubjectEnum convert(Integer source) {
        return TrialSubjectEnum.of(source);
    }
}
