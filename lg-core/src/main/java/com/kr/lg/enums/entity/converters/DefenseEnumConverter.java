package com.kr.lg.enums.entity.converters;


import com.kr.lg.enums.AbstractEnumAttributeConverter;
import com.kr.lg.enums.entity.element.DefenseEnum;
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
