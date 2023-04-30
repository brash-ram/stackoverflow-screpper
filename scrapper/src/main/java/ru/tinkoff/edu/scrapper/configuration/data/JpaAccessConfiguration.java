package ru.tinkoff.edu.scrapper.configuration.data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.scrapper.data.respository.jpa.JpaChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.scrapper.service.ChatService;
import ru.tinkoff.edu.scrapper.service.LinkService;
import ru.tinkoff.edu.scrapper.service.jpaImpl.JpaChatService;
import ru.tinkoff.edu.scrapper.service.jpaImpl.JpaLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    public ChatService chatService(JpaChatRepository jpaChatRepository) {
        return new JpaChatService(jpaChatRepository);
    }

    @Bean
    public LinkService linkService(
            JpaLinkRepository jpaLinkRepository,
            ChatService chatService
    ) {
        return new JpaLinkService(jpaLinkRepository, chatService);
    }
}
