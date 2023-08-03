// 예제 채팅 목록 데이터
const chats = [
    {
        name: '방#1',
        message: '안녕하세요!',
        updateTime: '1분 전',
    },
    {
        name: '방#2',
        message: '반가워요!',
        updateTime: '2분 전',
    },
];

function renderChatList() {
    const chatList = document.getElementById('chatList');
    chatList.innerHTML = '';

    chats.forEach((chat) => {
        const chatItem = document.createElement('li');
        chatItem.className = 'chatItem';

        chatItem.innerHTML = `
      <div>
        <p class="chatName">${chat.name}</p>
        <p class="chatMessage">${truncateMessage(chat.message)}</p>
      </div>
      <p class="chatUpdateTime">${chat.updateTime}</p>
    `;

        chatList.appendChild(chatItem);
    });
}

function truncateMessage(message) {
    if (message.length > 10) {
        return message.substring(0, 10) + '...';
    }

    return message;
}

// 채팅 목록을 렌더링합니다
renderChatList();

function loadChat() {
    const content = document.getElementById('content');

    // Fetch the chat.html file and insert it into the 'content' div
    fetch('/chat.html')
        .then((response) => {
            if (response.ok) {
                return response.text();
            } else {
                console.error('Error loading chat.html:', response.status);
            }
        })
        .then((html) => {
            content.innerHTML = html;
        })
        .catch((error) => {
            console.error('Error loading chat.html:', error);
        });
}

window.onload = loadChat;
