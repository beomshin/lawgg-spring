package com.kr.lg.common.enums.convert.status;

import com.kr.lg.common.enums.convert.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.status.UserStatus;

public class UserStatusConverter extends AbstractEnumAttributeConverter<UserStatus> {

    public UserStatusConverter() {
        super(UserStatus.class);
    }
}
