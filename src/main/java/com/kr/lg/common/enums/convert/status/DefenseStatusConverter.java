package com.kr.lg.common.enums.convert.status;


import com.kr.lg.common.enums.convert.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.status.DefenseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Slf4j
@Converter
@Component
public class DefenseStatusConverter extends AbstractEnumAttributeConverter<DefenseStatus> {

    public DefenseStatusConverter() {
        super(DefenseStatus.class);
    }

}
