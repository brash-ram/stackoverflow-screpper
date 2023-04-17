package ru.tinkoff.edu.scrapper.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.impl.ChatRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {IntegrationEnvironment.IntegrationEnvironmentConfiguration.class, ChatRepositoryJdbcImpl.class})
public class ChatRepositoryJdbcImplTests {
    
    private static Chat TEST_CHAt;

    @Autowired
    private ChatRepository chatRepositoryJdbcImpl;

    @BeforeAll
    public static void setTestChat() {
        IntegrationEnvironment.runMigration();
        TEST_CHAt = new Chat()
                .setChatId(1L);
    }

    @Test
    @Transactional
    @Rollback
    public void saveTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAt);
        assertNotNull(chat.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAt);
        chatRepositoryJdbcImpl.remove(chat.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        chatRepositoryJdbcImpl.save(TEST_CHAt);
        chatRepositoryJdbcImpl.save(TEST_CHAt);

        assertEquals(chatRepositoryJdbcImpl.findAll().size(), 2);
    }
}
