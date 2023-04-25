package ru.tinkoff.edu.scrapper.repository.jpa;

import org.junit.jupiter.api.BeforeAll;
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
@Import(IntegrationEnvironment.IntegrationEnvironmentConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatAndLinkRepositoryJpaIntegrationTests {

    private static Chat TEST_CHAt;
    private static Link TEST_LINK;

    @Autowired
    private JpaChatRepository jpaChatRepository;

    @Autowired
    private JpaLinkRepository jpaLinkRepository;

    @BeforeAll
    public static void setTestData() throws URISyntaxException {
        TEST_CHAt = new Chat()
                .setChatId(1L);
        TEST_LINK = new Link()
                .setUrl(new URI("http://localhost:8080"))
                .setLastUpdate(new Timestamp(System.currentTimeMillis()))
                .setChat(TEST_CHAt);
    }

    @Test
    @Transactional
    @Rollback
    public void saveChatAndLinkTest() {
        jpaChatRepository.save(TEST_CHAt);
        jpaLinkRepository.save(TEST_LINK);

        Optional<Chat> savingChat = jpaChatRepository.findById(TEST_CHAt.getId());

        assertTrue(savingChat.isPresent());
        assertEquals(savingChat.get().getLinks().size(), 1);
    }


    @Test
    @Transactional
    @Rollback
    public void cascadeDeleteChatAndLinkTest() {
        jpaChatRepository.save(TEST_CHAt);
        jpaLinkRepository.save(TEST_LINK);
        jpaLinkRepository.save(TEST_LINK);

        Optional<Chat> savingChat = jpaChatRepository.findById(TEST_CHAt.getId());

        assertTrue(savingChat.isPresent());
        assertEquals(savingChat.get().getLinks().size(), 2);

        jpaChatRepository.delete(savingChat.get());

        savingChat = jpaChatRepository.findById(TEST_CHAt.getId());
        List<Link> links = jpaLinkRepository.findAll();

        assertTrue(savingChat.isPresent());
        assertTrue(links.isEmpty());
    }
}
