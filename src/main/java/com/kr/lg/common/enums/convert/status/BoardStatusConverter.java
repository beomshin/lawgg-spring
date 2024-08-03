package com.kr.lg.common.enums.convert.status;

import com.kr.lg.common.enums.convert.AbstractEnumAttributeConverter;
import com.kr.lg.common.enums.entity.status.BoardStatus;

public class BoardStatusConverter extends AbstractEnumAttributeConverter<BoardStatus> {

    public BoardStatusConverter() {
        super(BoardStatus.class);
    }
}
