package ru.tinkoff.edu.scrapper.service;

import ru.tinkoff.edu.scrapper.data.entity.Chat;

public interface ChatService {
    Chat register(long tgChatId);
    void unregister(long tgChatId);
    Chat getById(long id);

    Chat getByChatId(long tgChatId);
}
