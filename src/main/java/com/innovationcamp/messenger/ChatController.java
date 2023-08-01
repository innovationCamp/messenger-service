package com.innovationcamp.messenger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat() {
        return "chat";
    }
}
