package com.kr.lg.common.enums.entity.converters;

import com.kr.lg.common.enums.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.element.StatusEnum;

public class StatusEnumConverter extends AbstractEnumAttributeConverter<StatusEnum> {

    public StatusEnumConverter() {
        super(StatusEnum.class);
    }
}
