package com.kr.lg.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {


    @Value("${kafka.bootstrapAddress}")
    private String bootstrapServers;

    @Value("${kafka.producer.acksConfig}")
    private String acksConfig;

    @Value("${kafka.producer.retry}")
    private Integer retry;

    @Value("${kafka.producer.enable-idempotence}")
    private Boolean enableIdempotence;

    @Value("${kafka.producer.max-in-flight-requests-per-connection}")
    private Integer maxInFlightRequestsPerConnection;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // 서버 설정
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // key serializer
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // value serializer

        configProps.put(ProducerConfig.ACKS_CONFIG, acksConfig); // 메세지 ack 설정 (0:기다리지않음, 1: 리더메세지 기다림, -1: 전부 기다림)
        configProps.put(ProducerConfig.RETRIES_CONFIG, retry); // retry
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence); // 멱등성 프로듀스 사용
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequestsPerConnection); // connection request 제한수 (기본 5개, 1개면 순서보장, 높을수록 메모리 소모)

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
