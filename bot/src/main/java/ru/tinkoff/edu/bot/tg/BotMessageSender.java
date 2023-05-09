package ru.tinkoff.edu.bot.tg;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.bot.dto.LinkUpdate;

@Service
@RequiredArgsConstructor
public class BotMessageSender {

    private final Bot bot;

    public void sendMessage(LinkUpdate linkUpdate) {
        bot.send(new SendMessageAdapter(linkUpdate.tgChatIds().get(0), linkUpdate.description()).getSendMessage());
    }
}
