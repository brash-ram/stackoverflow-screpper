package ru.tinkoff.edu.scrapper.repository.jpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.jpa.JpaChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(excludeAutoConfiguration = LiquibaseAutoConfiguration.class)
@Import(IntegrationEnvironment.JpaIntegrationEnvironmentConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatAndLinkRepositoryJpaIntegrationTests {

    private static Chat TEST_CHAT;
    private static Link TEST_LINK;

    @Autowired
    private JpaChatRepository jpaChatRepository;

    @Autowired
    private JpaLinkRepository jpaLinkRepository;


    @BeforeAll
    public static void setTestData() throws URISyntaxException {
        TEST_CHAT = new Chat()
                .setChatId(1L);
        TEST_LINK = new Link()
                .setUrl(new URI("http://localhost:8080"))
                .setLastUpdate(new Timestamp(System.currentTimeMillis()))
                .setChat(TEST_CHAT);
    }

    @Test
    @Transactional
    @Rollback
    public void saveChatAndLinkTest() {
        Link link = jpaLinkRepository.save(TEST_LINK.setChat(null));
        Chat chat = jpaChatRepository.save(TEST_CHAT.setLinks(List.of(link)));

        Optional<Chat> savingChat = jpaChatRepository.findById(chat.getId());

        assertTrue(savingChat.isPresent());
        assertEquals(savingChat.get().getLinks().size(), 1);
    }


    @Test
    @Transactional
    @Rollback
    public void cascadeDeleteChatAndLinkTest() {
        Link link1 = jpaLinkRepository.save(TEST_LINK);
        Link link2 = jpaLinkRepository.save(TEST_LINK);
        TEST_CHAT.setLinks(List.of(link1, link2));
        jpaChatRepository.save(TEST_CHAT);

        Optional<Chat> savingChat = jpaChatRepository.findById(TEST_CHAT.getId());

        assertTrue(savingChat.isPresent());
        assertNotNull(savingChat.get().getLinks());
        assertEquals(savingChat.get().getLinks().size(), 2);

        jpaChatRepository.delete(savingChat.get());

        savingChat = jpaChatRepository.findById(savingChat.get().getId());
        List<Link> links = jpaLinkRepository.findAll();

        assertFalse(savingChat.isPresent());
        assertTrue(links.isEmpty());
    }
}
