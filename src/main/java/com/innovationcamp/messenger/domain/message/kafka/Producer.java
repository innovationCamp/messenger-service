//package com.innovationcamp.messenger.domain.message.kafka;
//
//import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class Producer {
//    // 현재 사용 안함, 이후에 사용할 수 있어서 남겨둠
//    @NonNull
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void send(String topic, MessageRequestDto data) {
//        log.info("sending data='{}' to topic='{}'", data, topic);
//        try {
//            kafkaTemplate.send(topic, data).get();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Kafka Producer Secd error");
//        }
//    }
//}
