package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

public class ListCommandHandler extends MessageHandler{

    @Override
    public void handleMessage(Update update) {
        Message message = update.message();
        if (message.text().equals("/list")) {
            bot.send(new SendMessageAdapter(message.chat().id(), DEFAULT_MASSAGE + "list")
                    .getSendMessage());
        } else {
            nextHandler.handleMessage(update);
        }
    }
}
