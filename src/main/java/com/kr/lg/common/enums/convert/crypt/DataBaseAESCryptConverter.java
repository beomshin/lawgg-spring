package com.kr.lg.common.enums.convert.crypt;

import com.kr.lg.common.crypto.AESCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter
@Component
@RequiredArgsConstructor
public class DataBaseAESCryptConverter implements AttributeConverter<String, String> {

    /**
     * 개인정보 암호화 Converter
     */

    @Value("${aes.key}")
    private String aesKey;

    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null || s.isEmpty()) return null;
        try {
            return AESCrypt.encrypt(s, aesKey);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) return null;
        try {
            return AESCrypt.decrypt(s, aesKey);
        } catch (Exception e) {
            return null;
        }
    }


}
