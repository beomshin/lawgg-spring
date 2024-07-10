package com.kr.lg.common.converters;


import com.kr.lg.enums.DefenseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Slf4j
@Converter
@Component
public class DefenseEnumConverter extends AbstractEnumAttributeConverter<DefenseEnum> {

    public DefenseEnumConverter() {
        super(DefenseEnum.class);
    }

}
