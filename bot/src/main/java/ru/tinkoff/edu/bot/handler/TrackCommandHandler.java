package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

public class TrackCommandHandler extends MessageHandler{
    @Override
    public void handleMessage(Update update) {
        Message message = update.message();
        if (message.text().equals("/track")) {
            bot.send(new SendMessageAdapter(message.chat().id(), DEFAULT_MASSAGE + "track")
                    .getSendMessage());
        } else {
            nextHandler.handleMessage(update);
        }
    }
}
