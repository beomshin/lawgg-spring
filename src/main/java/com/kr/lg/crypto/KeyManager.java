package com.kr.lg.crypto;

import com.kr.lg.db.repositories.RootConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class KeyManager {

    public static String jwtKey;
    public static String aesKey;

    public KeyManager(RootConfigRepository rootConfigRepository) {
        rootConfigRepository.findConfig(Arrays.asList("jwt_key", "aes_key")).forEach(it -> {
            if (it.getKey().equals("jwt_key")) jwtKey = it.getValue();
            else if (it.getKey().equals("aes_key")) aesKey = it.getValue();
        });
    }
}
