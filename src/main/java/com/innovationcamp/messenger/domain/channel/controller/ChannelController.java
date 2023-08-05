package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.channel.dto.NewChannelRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChannelController {

    @PostMapping("/channel")
    public ResponseEntity<NewChannelRequestDto> createChannel(){
        return null;
    }


}
