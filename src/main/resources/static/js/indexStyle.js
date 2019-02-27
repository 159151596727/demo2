document.querySelector('.chat[data-chat=' + $("li.person:first").attr("data-chat") + ']').classList.add('active-chat');
document.querySelector('.person[data-chat=' + $("div.chat:first").attr("data-chat") + ']').classList.add('active');
var friends = {
        list: document.querySelector('ul.people'),
        all: document.querySelectorAll('.left .person'),
        name: ''
    },

    chat = {
        container: document.querySelector('.container .right'),
        current: null,
        person: null,
        name: document.querySelector('.container .right .top .name')
    };


friends.all.forEach(function (f) {
    f.addEventListener('mousedown', function () {
        f.classList.contains('active') || setAciveChat(f);
    });
});

function setAciveChat(f) {
    if (friends.list.querySelector('.active') != null) {
        friends.list.querySelector('.active').classList.remove('active');
    }
    f.classList.add('active');
    chat.current = chat.container.querySelector('.active-chat');
    chat.person = f.getAttribute('data-chat');
    if (chat.current != null) {
        chat.current.classList.remove('active-chat');
    }
    chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
    friends.name = f.querySelector('.name').innerText;
    chat.name.innerHTML = friends.name;
}