package ru.tinkoff.edu.scrapper.configuration.data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.ChatRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.LinkRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.service.ChatService;
import ru.tinkoff.edu.scrapper.service.LinkService;
import ru.tinkoff.edu.scrapper.service.jdbcImpl.JdbcChatService;
import ru.tinkoff.edu.scrapper.service.jdbcImpl.JdbcLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    @Bean
    public ChatRepository chatRepository(JdbcTemplate jdbcTemplate) {
        return new ChatRepositoryJdbcImpl(jdbcTemplate);
    }

    @Bean
    public LinkRepository linkRepository(JdbcTemplate jdbcTemplate) {
        return new LinkRepositoryJdbcImpl(jdbcTemplate);
    }

    @Bean
    public ChatService chatService(ChatRepository chatRepository) {
        return new JdbcChatService(chatRepository);
    }

    @Bean
    public LinkService linkService(
            LinkRepository linkRepository,
            ChatService chatService
    ) {
        return new JdbcLinkService(linkRepository, chatService);
    }
}
