package com.kr.lg.convert;

import com.kr.lg.enums.common.element.BoardTypeEnum;
import org.springframework.core.convert.converter.Converter;

public class WebBoardTypeEnumConvert implements Converter<Integer, BoardTypeEnum> {

    @Override
    public BoardTypeEnum convert(Integer source) {
        return BoardTypeEnum.of(source);
    }
}
