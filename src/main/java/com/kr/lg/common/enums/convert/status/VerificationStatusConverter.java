package com.kr.lg.common.enums.convert.status;

import com.kr.lg.common.enums.convert.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.status.VerificationStatus;

public class VerificationStatusConverter extends AbstractEnumAttributeConverter<VerificationStatus> {

    public VerificationStatusConverter() {
        super(VerificationStatus.class);
    }
}
