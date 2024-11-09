package com.kr.lg.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {

    void sendMessage(String topic, Object message) throws JsonProcessingException;
}
