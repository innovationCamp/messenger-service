package com.innovationcamp.messenger.domain.contact.service;

import com.innovationcamp.messenger.domain.contact.dto.ResponseDtoContact;
import com.innovationcamp.messenger.domain.contact.entity.Contact;
import com.innovationcamp.messenger.domain.contact.repository.ContactRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    @NonNull
    private ContactRepository contactRepository;

    @NonNull
    private UserRepository userRepository;


    //해당 사용자의 저장된 모든 연락처 조회
    public List<ResponseDtoContact> getAllContact(Long userId) {
        // 사용자에게 연락처 정보 조회
        List<Contact> contacts = contactRepository.findByUserId(userId);
        List<ResponseDtoContact> responseContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            // 연락처의 contactId를 이용하여 해당 사용자 정보를 조회
            User contactUser = userRepository.findById(contact.getContactId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 연락처 사용자를 찾을 수 없습니다."));

            // 임시
            responseContacts.add(new ResponseDtoContact(contactUser.getEmail(), contactUser.getUsername()));
        }
        return responseContacts;
    }

    // 본인이 본인 id를 추가하는거 막아야됨
    public ResponseDtoContact createContact(Long userId, Long contactId) {
        if (userId.equals(contactId)) {
            throw new IllegalArgumentException("자신을 연락처에 추가할 수 없습니다.");
        }
        // 이미 추가된 연락처인지 확인하기 위해 해당 사용자의 연락처 목록을 조회
        List<Contact> userContacts = contactRepository.findByUserId(userId);
        // 연락처 목록에서 이미 추가 되어있는 연락처인지 확인
        if (userContacts.stream().anyMatch(contact -> contact.getContactId().equals(contactId))) {
            throw new IllegalArgumentException("이미 추가된 연락처입니다.");
        }
        // 사용자를 찾아 연락처를 추가
        User contactUser = userRepository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Contact contact = new Contact(userId, contactUser.getUserid());
        contactRepository.save(contact);

        return new ResponseDtoContact(contactUser);
    }
    //특정 연락처 삭제
    public ResponseDtoContact removeContact(Long userId, Long contactId) {
        Contact contact = contactRepository.findByUserIdAndContactId(userId, contactId)
                .orElseThrow(() -> new IllegalArgumentException("해당 연락처를 찾을 수 없습니다."));

        // 연락처 삭제
        contactRepository.delete(contact);

        // 임시
        // 삭제된 연락처 정보를 ResponseDtoContact 객체에 설정
        User contactUser = userRepository.findById(contact.getContactId())
                .orElseThrow(() -> new IllegalArgumentException("해당 연락처 사용자를 찾을 수 없습니다."));;

        // 임시
        ResponseDtoContact responseContact = new ResponseDtoContact(contactUser.getEmail(), contactUser.getUsername());

        return responseContact;
    }

}
