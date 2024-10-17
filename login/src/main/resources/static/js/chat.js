document.getElementById('sendButton').addEventListener('click', function() {
    sendMessage();
});

document.getElementById('messageInput').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});

function sendMessage() {
    var messageInput = document.getElementById('messageInput');
    var messageText = messageInput.value.trim();

    if (messageText === '') return;

    var messageElement = document.createElement('div');
    messageElement.className = 'message sent';
    messageElement.innerHTML = '<p>' + messageText + '</p><span>' + getCurrentTime() + '</span>';

    var chatBody = document.getElementById('chatBody');
    chatBody.appendChild(messageElement);
    chatBody.scrollTop = chatBody.scrollHeight;

    messageInput.value = '';
}

function getCurrentTime() {
    var now = new Date();
    return now.getHours() + ':' + (now.getMinutes() < 10 ? '0' : '') + now.getMinutes();
}
