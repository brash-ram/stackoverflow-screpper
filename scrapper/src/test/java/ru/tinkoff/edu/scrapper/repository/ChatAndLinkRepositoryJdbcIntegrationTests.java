package ru.tinkoff.edu.scrapper.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.ChatRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.LinkRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        IntegrationEnvironment.IntegrationEnvironmentConfiguration.class,
        ChatRepositoryJdbcImpl.class,
        LinkRepositoryJdbcImpl.class
})
public class ChatAndLinkRepositoryJdbcIntegrationTests {

    private static Chat TEST_CHAt;
    private static Link TEST_LINK;

    @Autowired
    private ChatRepository chatRepositoryJdbcImpl;

    @Autowired
    private LinkRepository linkRepository;

    @BeforeAll
    public static void setTestData() throws URISyntaxException {
        IntegrationEnvironment.runMigration();
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
        chatRepositoryJdbcImpl.save(TEST_CHAt);
        linkRepository.save(TEST_LINK);

        Chat savingChat = chatRepositoryJdbcImpl.findById(TEST_CHAt.getId());

        assertEquals(savingChat.getLinks().size(), 1);
    }


    @Test
    @Transactional
    @Rollback
    public void cascadeDeleteChatAndLinkTest() {
        chatRepositoryJdbcImpl.save(TEST_CHAt);
        linkRepository.save(TEST_LINK);
        linkRepository.save(TEST_LINK);

        Chat savingChat = chatRepositoryJdbcImpl.findById(TEST_CHAt.getId());

        assertEquals(savingChat.getLinks().size(), 2);

        chatRepositoryJdbcImpl.remove(savingChat.getId());

        savingChat = chatRepositoryJdbcImpl.findById(TEST_CHAt.getId());
        List<Link> links = linkRepository.findAll();

        assertNull(savingChat);
        assertTrue(links.isEmpty());
    }
}
