//package com.innovationcamp.messenger.domain.message.kafka;
//
//import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class Consumer {
//    // 현재 사용 안함, 이후에 사용할 수 있어서 남겨둠
//    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC)
//    public void consumeMessage(MessageRequestDto message) {
//        /* 수신된 메시지 처리 로직을 구현
//         message 변수에 수신된 메시지 데이터가 들어 있음
//         이 부분에서 원하는 로직을 수행하면 됨 */
//        log.info("작동중?" + message.getMessage());
//    }
//}
