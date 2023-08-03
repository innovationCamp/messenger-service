// chatContainer 선택 및 스크롤 함수 정의
function scrollChatToBottom() {
    const chatContainer = document.querySelector('.chat-container');
    if (chatContainer) {
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }
}

// 페이지가 로드될 때 chatContainer를 아래로 스크롤하도록 호출
window.onload = function () {
    scrollChatToBottom();

    // 채팅 입력란과 채팅 컨테이너 선택
    const chatInput = document.getElementById('chat-input');
    const chatContainer = document.querySelector('.chat-container');

    // 채팅 입력란에서 Enter 키 눌렀을 때 이벤트 리스너 등록
    chatInput.addEventListener('keypress', function (e) {
        if (e.key === 'Enter' && chatInput.value.trim() !== '') {
            // Enter 키를 누르고 입력이 비어있지 않을 때 (공백 제외)
            const messageBox = document.createElement('div');
            messageBox.className = 'message-box my-message';
            const message = document.createElement('p');
            message.textContent = chatInput.value;
            const messageTime = document.createElement('span');
            messageTime.textContent = getCurrentTime();
            message.appendChild(messageTime);
            messageBox.appendChild(message);
            chatContainer.appendChild(messageBox);

            // 새로운 메시지 추가 후 아래로 스크롤
            scrollChatToBottom();

            chatInput.value = '';
        }
    });
};

// 현재 시간 가져오는 함수
function getCurrentTime() {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    const timeString = hours.toString().padStart(2, '0') + ':' + minutes.toString().padStart(2, '0');
    return timeString;
}
