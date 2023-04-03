package ru.tinkoff.edu.bot.configuaration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.bot.handler.MessageHandler;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final ApplicationConfig config;
    private final ApplicationContext  applicationContext;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot telegramBot = new TelegramBot(config.token());
        telegramBot.setUpdatesListener(updates -> {
            MessageHandler messageHandler = applicationContext.getBean(MessageHandler.class);
            for (Update update : updates) {
                messageHandler.handleMessage(update);
            }
//            telegramBot.execute(new SendMessage(updates.get(0).message().chat().id(), "test"));

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        return telegramBot;
    }
}
