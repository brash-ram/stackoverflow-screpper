package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Update;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

final class DefaultHandler extends MessageHandler{

    @Override
    public void handleMessage(Update update) {
        bot.send(new SendMessageAdapter(update.message().chat().id(), "Нет подходящего обработчика")
                .getSendMessage()
        );
    }
}
