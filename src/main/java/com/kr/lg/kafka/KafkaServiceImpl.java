package com.kr.lg.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String topic, Object message) throws JsonProcessingException {
        log.info("sendMessage");
        kafkaTemplate.send(topic, new ObjectMapper().writeValueAsString(message));
    }
}
