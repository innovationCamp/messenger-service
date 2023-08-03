package com.innovationcamp.messenger.domain.contact.service;

import com.innovationcamp.messenger.domain.contact.dto.ContactResponseDto;
import com.innovationcamp.messenger.domain.contact.dto.CreateContactResponseDto;
import com.innovationcamp.messenger.domain.contact.entity.Contact;
import com.innovationcamp.messenger.domain.contact.repository.ContactRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    @NonNull
    private ContactRepository contactRepository;
    @NonNull
    private UserRepository userRepository;

    //해당 사용자의 저장된 모든 연락처 조회
    public List<ContactResponseDto> getAllContact(User user) {
        // 사용자id로 연락처 정보 조회
        List<Contact> contacts = contactRepository.findAllByUser(user);
        // 연락처 조회한 contactId로 userDB에서 username, email을 리스트로 반환
        List<ContactResponseDto> contactResponseDtoList = contacts.stream()
                .map(contact -> {
                    User contactUser = contact.getContactUser();
                    return new ContactResponseDto(contactUser.getEmail(), contactUser.getUsername());
                })
                .collect(Collectors.toList());

        return contactResponseDtoList;
    }

    // 본인이 본인 id를 추가하는거 막아야됨
    public CreateContactResponseDto createContact(User user, String contactEmail) {
        // contactEmail로 해당 사용자 정보 불러오기
        User contactUser = getUserByEmail(contactEmail);
        Long contactId = contactUser.getId();
        if (user.equals(contactUser)) {
            throw new IllegalArgumentException("자신을 연락처에 추가할 수 없습니다.");
        }
        // 이미 추가된 연락처인지 확인하기 위해 해당 사용자의 연락처 목록을 조회
        List<Contact> userContacts = contactRepository.findAllByUser(user);
        // 연락처 목록에서 이미 추가 되어있는 연락처인지 확인
        if (userContacts.stream().anyMatch(contact -> contact.getContactUser().getId().equals(contactId))) {
            throw new IllegalArgumentException("이미 추가된 연락처입니다.");
        }

        // 새로운 연락처 생성 및 저장
        Contact newContact = new Contact(userRepository.findById(user.getId()).orElseThrow(),
                userRepository.findById(contactId).orElseThrow());
        contactRepository.save(newContact);

        String username = userRepository.findById(user.getId()).orElseThrow().getUsername();
        return new CreateContactResponseDto(username, contactUser);
    }
    //ResponseDtoContact 예시
    //{
    //    "username": "User 3", 본인 name
    //    "contactname": "User 1", 상대방 name
    //}

    //특정 연락처 삭제
    public ContactResponseDto deleteContact(User user, String contactEmail) {
        // contactEmail로 해당 사용자 정보 불러오기
        User contactUser = getUserByEmail(contactEmail);

        Contact contact = contactRepository.findByUserAndContactUser(user, contactUser)
                .orElseThrow(() -> new IllegalArgumentException("해당 연락처를 찾을 수 없습니다."));
        // 연락처 삭제
        contactRepository.delete(contact);

        // 임시
        ContactResponseDto contactResponseDto = new ContactResponseDto(contactUser.getEmail(), contactUser.getUsername());

        return contactResponseDto;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("대상자를 찾을 수 없습니다."));
    }

}
