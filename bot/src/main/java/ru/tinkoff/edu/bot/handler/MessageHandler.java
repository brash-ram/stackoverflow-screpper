package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.bot.tg.Bot;

public abstract class MessageHandler {
    protected MessageHandler nextHandler;

    @Autowired
    protected Bot bot;

    protected final String DEFAULT_MASSAGE = "Команда обработана: ";

    /**
     * Added next handler
     * @param nextHandler next handler
     * @return next handler
     */
    public MessageHandler setNextHandler(MessageHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract void handleMessage(Update update);
}
