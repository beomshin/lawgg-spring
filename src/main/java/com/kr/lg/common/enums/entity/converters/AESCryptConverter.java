package com.kr.lg.common.enums.entity.converters;

import com.kr.lg.common.crypto.AESCrypt;
import com.kr.lg.common.crypto.KeyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter
@Component
@RequiredArgsConstructor
public class AESCryptConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null || s.isEmpty()) return null;
        try {
            return AESCrypt.encrypt(s, KeyManager.aesKey);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) return null;
        try {
            return AESCrypt.decrypt(s, KeyManager.aesKey);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
