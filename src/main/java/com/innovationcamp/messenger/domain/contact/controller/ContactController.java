package com.innovationcamp.messenger.domain.contact.controller;

import com.innovationcamp.messenger.domain.contact.dto.ContactResponseDto;
import com.innovationcamp.messenger.domain.contact.dto.CreateContactResponseDto;
import com.innovationcamp.messenger.domain.contact.service.ContactService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    @NonNull
    private ContactService contactService;

    // 연락처 조회(전체) // 헤더 정보에 본인 정보가 어떻게 저장되는지 모름 임시로 userId로 사용
    @GetMapping("")
    public List<ContactResponseDto> getAllContact(@RequestHeader("token") Long userId){
        return contactService.getAllContact(userId);
    }
    // 새 연락처 추가
    @PostMapping("")
    public CreateContactResponseDto createContact(@RequestHeader("token") Long userId, @RequestHeader("contactEmail") String contactEmail){
        return contactService.createContact(userId, contactEmail);
    }
    // 연락처 삭제
    @DeleteMapping("")
    public ContactResponseDto deleteContact(@RequestHeader("token") Long userId, @RequestHeader("contactEmail") String contactEmail){
        return contactService.deleteContact(userId,contactEmail);
    }
}
