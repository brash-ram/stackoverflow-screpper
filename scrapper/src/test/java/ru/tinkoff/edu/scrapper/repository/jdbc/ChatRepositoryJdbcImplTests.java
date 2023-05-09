package ru.tinkoff.edu.scrapper.repository.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Chat;
import ru.tinkoff.edu.scrapper.data.respository.ChatRepository;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.ChatRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        IntegrationEnvironment.IntegrationEnvironmentConfiguration.class,
        ChatRepositoryJdbcImpl.class
})
public class ChatRepositoryJdbcImplTests {
    
    private static Chat TEST_CHAT;

    @Autowired
    private ChatRepository chatRepositoryJdbcImpl;

    @BeforeAll
    public static void setTestChat() {
        TEST_CHAT = new Chat()
                .setChatId(1L);
    }

    @Test
    @Transactional
    @Rollback
    public void saveTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAT);
        assertNotNull(chat.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void findByIdTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAT);
        Optional<Chat> findChat = chatRepositoryJdbcImpl.findById(chat.getId());
        assertTrue(findChat.isPresent());
        Chat presentChat = findChat.get();
        assertEquals(chat.getId(), presentChat.getId());
        assertEquals(chat.getChatId(), presentChat.getChatId());
    }

    @Test
    @Transactional
    @Rollback
    public void findByChatIdTest() {
        chatRepositoryJdbcImpl.save(TEST_CHAT);
        Chat chat = chatRepositoryJdbcImpl.findByChatId(1L);
        assertNotNull(chat);
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAT);
        chatRepositoryJdbcImpl.remove(chat.getId());
        assertEquals(0, chatRepositoryJdbcImpl.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        chatRepositoryJdbcImpl.save(TEST_CHAT);
        Chat chat = new Chat().setChatId(2L);
        chatRepositoryJdbcImpl.save(chat);

        assertEquals(2, chatRepositoryJdbcImpl.findAll().size());
    }
}
