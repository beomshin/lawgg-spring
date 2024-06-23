package com.kr.lg.enums.entity.converters;

import com.kr.lg.enums.AbstractEnumAttributeConverter;
import com.kr.lg.enums.entity.element.StatusEnum;

public class StatusEnumConverter extends AbstractEnumAttributeConverter<StatusEnum> {

    public StatusEnumConverter() {
        super(StatusEnum.class);
    }
}
