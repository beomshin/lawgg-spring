package com.kr.lg.common.converters;


import com.kr.lg.enums.AuthEnum;

public class AuthEnumConverter extends AbstractEnumAttributeConverter<AuthEnum> {

    public AuthEnumConverter() {
        super(AuthEnum.class);
    }
}