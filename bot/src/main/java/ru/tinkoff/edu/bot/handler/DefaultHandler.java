package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.bot.tg.Bot;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

@Component
final class DefaultHandler extends MessageHandler {

    public DefaultHandler(Bot bot) {
        super(bot);
    }

    @Override
    public void handleMessage(Update update) {
        bot.send(new SendMessageAdapter(update.message().chat().id(), "Нет подходящего обработчика")
                .getSendMessage()
        );
    }
}
