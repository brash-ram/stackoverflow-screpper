package ru.tinkoff.edu.bot.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageHandlerConfiguration {

    @Bean
    public MessageHandler messageHandler() {
        MessageHandler messageHandler = new StartCommandHandler();
        messageHandler.setNextHandler(new DefaultHandler());
        return  messageHandler;
    }
}
