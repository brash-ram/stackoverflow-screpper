package ru.tinkoff.edu.scrapper.repository.jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    
    private static Chat TEST_CHAt;

    @Autowired
    private ChatRepository chatRepositoryJdbcImpl;

    @BeforeAll
    public static void setTestChat() {
        TEST_CHAt = new Chat()
                .setChatId(1L);
    }

    @BeforeEach
    public void clearDb() {
        try {
            chatRepositoryJdbcImpl.remove(1L);
        } catch (Exception ignored) {
        }
        try {
            chatRepositoryJdbcImpl.remove(2L);
        } catch (Exception ignored) {
        }
        try {
            chatRepositoryJdbcImpl.remove(3L);
        } catch (Exception ignored) {
        }
        try {
            chatRepositoryJdbcImpl.remove(4L);
        } catch (Exception ignored) {
        }
        try {
            chatRepositoryJdbcImpl.remove(5L);
        } catch (Exception ignored) {
        }
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
    public void findByIdTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAt);
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
        chatRepositoryJdbcImpl.save(TEST_CHAt);
        Chat chat = chatRepositoryJdbcImpl.findByChatId(1L);
        assertNotNull(chat);
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Chat chat = chatRepositoryJdbcImpl.save(TEST_CHAt);
        chatRepositoryJdbcImpl.remove(chat.getId());
        assertEquals(0, chatRepositoryJdbcImpl.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        chatRepositoryJdbcImpl.save(TEST_CHAt);
        chatRepositoryJdbcImpl.save(TEST_CHAt);

        assertEquals(2, chatRepositoryJdbcImpl.findAll().size());
    }
}
