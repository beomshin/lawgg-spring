package com.kr.lg.common.enums.convert.type;

import com.kr.lg.common.enums.convert.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.type.PostType;

public class PostTypeConverter extends AbstractEnumAttributeConverter<PostType> {

    public PostTypeConverter() {
        super(PostType.class);
    }
}
