package ru.tinkoff.edu.bot.configuaration;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.bot.handler.MessageHandler;
import ru.tinkoff.edu.bot.tg.Bot;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final Bot bot;
    private final MessageHandler messageHandler;

    @Override
    public void run(String... args) {
        bot.getTelegramBot().setUpdatesListener(updates -> {
            for (Update update : updates) {
                messageHandler.handleMessage(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}