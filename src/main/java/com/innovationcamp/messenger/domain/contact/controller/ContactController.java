package com.innovationcamp.messenger.domain.contact.controller;

import com.innovationcamp.messenger.domain.contact.dto.ResponseDtoContact;
import com.innovationcamp.messenger.domain.contact.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    // 연락처 조회(전체)
    @GetMapping("/contact")
    public List<ResponseDtoContact> getAllContact(@RequestHeader("token") Long userId){
        return contactService.getAllContact(userId);
    }
    // 새 연락처 추가
    @PostMapping("/contact")
    public ResponseDtoContact addContact(@RequestHeader("token") Long userId, @RequestHeader("contactId") Long contactId){
        return contactService.createContact(userId, contactId);
    }
    // 연락처 삭제
    @DeleteMapping("/contact")
    public ResponseDtoContact removeContact(@RequestHeader("token") Long userId, @RequestHeader("contactId") Long contactId){
        return contactService.removeContact(userId,contactId);
    }
}
