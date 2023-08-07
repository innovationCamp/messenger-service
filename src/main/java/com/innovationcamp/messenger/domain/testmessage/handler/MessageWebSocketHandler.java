//package com.innovationcamp.messenger.domain.testmessage.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageDto;
//import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
//import com.innovationcamp.messenger.domain.testmessage.service.TestMessageService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class MessageWebSocketHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final TestMessageService testMessageService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("payload {}", payload);
//// 삭제        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^ ");
//// 삭제       session.sendMessage(textMessage);
//        TestMessageDto testMessageDto = objectMapper.readValue(payload, TestMessageDto.class);
//        TestMessageRoomDto room = testMessageService.findRoomById(testMessageDto.getRoomId());
//        room.handleActions(session, testMessageDto, testMessageService);
//    }
//}