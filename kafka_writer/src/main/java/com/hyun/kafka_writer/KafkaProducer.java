package com.hyun.kafka_writer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "cqrs-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public void sendMessage(BookDTO bookDTO) {
        String message = "{\"bid\":" + "\"" + bookDTO.getBid() + "\"}";
        // 메시지 전송
        this.kafkaTemplate.send(TOPIC, message);
    }
}
