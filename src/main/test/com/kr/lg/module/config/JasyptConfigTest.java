package com.kr.lg.module.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {

    @Test
    public void test() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("HAkL1vbmK1oagig"); // 암호화 키
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); // 적용 알고리즘
        config.setKeyObtentionIterations(4387); // 해싱 반복 횟수
        config.setPoolSize(1); // 인스턴스 Pool 개수
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator"); // IV 생성기
        config.setStringOutputType("base64"); // 인코딩 방식
        encryptor.setConfig(config);
    }
}