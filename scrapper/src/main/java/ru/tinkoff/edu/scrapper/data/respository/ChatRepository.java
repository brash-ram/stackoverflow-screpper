package ru.tinkoff.edu.scrapper.data.respository;

import ru.tinkoff.edu.scrapper.data.entity.Chat;

import java.util.List;

public interface ChatRepository {

    Chat save(Chat chat);

    void remove(Long id);

    List<Chat> findAll();
}
