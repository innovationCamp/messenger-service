package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.message.dto.DeleteMessageRequestDto;
import com.innovationcamp.messenger.domain.message.dto.DeleteMessageResponseDto;
import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.message.service.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
    @NonNull
    private final MessageService messageService;
    @NonNull
    private final SimpMessageSendingOperations messagingTemplate;
//    @NonNull
//    private final Producer producer;
    @NonNull
    private final KafkaTemplate<String, MessageRequestDto> kafkaTemplate;

//    @MessageMapping("/chat/message")
//    @Async // 비동기적 처리
//    public void message(MessageRequestDto requestDto) {
//        if (MessageRequestDto.MessageType.ENTER.equals(requestDto.getType())) {
//            requestDto.setMessage(requestDto.getSenderName() + "님이 입장하셨습니다.");
//        }
//        MessageContentResponseDto responseDto = messageService.createMessage(requestDto);
//        producer.send(KafkaConstants.KAFKA_TOPIC, requestDto);
//        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getChannelId(), responseDto);
//    }

    @MessageMapping("/delete/message")
    @Async
    public void deleteMessage(DeleteMessageRequestDto requestDto) {

        DeleteMessageResponseDto responseDto = messageService.deleteMessage(requestDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getChannelId(), responseDto);
    }

    // ---
    // kafka 적용(미완료)

    @Value("${kafka.topic-with-key}")
    public String TOPIC_WITH_KEY;

    @MessageMapping("/chat/message")
    public void produceMessageWithKey(MessageRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();
        requestDto.setCreatedAt(now);

        String key = requestDto.getChannelId().toString();

        CompletableFuture<SendResult<String, MessageRequestDto>> future =
                kafkaTemplate.send(TOPIC_WITH_KEY, key, requestDto);
        listenFuture(future);
    }
    @KafkaListener(topics = "${kafka.topic-with-key}", containerFactory = "sendKafkaListenerContainerFactory")
    public void sendMessage(MessageRequestDto message) {
        /* 수신된 메시지 전송 로직을 구현
         message 변수에 수신된 메시지 데이터가 들어 있음
         이 부분에서 원하는 로직을 수행하면 됨 */
        log.info("전송 작동중" + message);
    }


    @KafkaListener(topics = "${kafka.topic-with-key}", containerFactory = "saveKafkaListenerContainerFactory")
    public void saveMessage(MessageRequestDto message) {
//        수신된 메시지 저장 로직을 구현
        messageService.saveMessage(message);
        log.info("저장 작동중" + message);
    }

    // 비동기 처리를 위함
    private void listenFuture(CompletableFuture<SendResult<String, MessageRequestDto>> future) {
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("메세지 전송 성공 topic: {}, offset: {}, partition: {}", result.getRecordMetadata().topic(), result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            } else {
                log.error("메세지 전송 실패 : {}", ex.getMessage());
            }
        });
    }
}
