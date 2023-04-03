package ru.tinkoff.edu.bot.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.bot.tg.Bot;

@Configuration
@RequiredArgsConstructor
public class MessageHandlerConfiguration {

    private final Bot bot;
    @Bean
    public MessageHandler messageHandler() {
        MessageHandler messageHandler = new StartCommandHandler(bot);
        messageHandler.setNextHandler(new TrackCommandHandler(bot))
                .setNextHandler(new UntrackCommandHandler(bot))
                .setNextHandler(new ListCommandHandler(bot))
                .setNextHandler(new HelpCommandHandler(bot))
                .setNextHandler(new DefaultHandler(bot));
        return messageHandler;
    }
}
