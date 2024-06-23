package com.kr.lg.common.enums.entity.converters;

import com.vdurmont.emoji.EmojiParser;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmojiConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return EmojiParser.parseToAliases(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return EmojiParser.parseToUnicode(dbData);
    }
}
