package ru.tinkoff.edu.scrapper.repository.jpa;

import org.junit.jupiter.api.AfterEach;
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
import ru.tinkoff.edu.scrapper.data.respository.jpa.JpaChatRepository;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(excludeAutoConfiguration = LiquibaseAutoConfiguration.class)
@Import(IntegrationEnvironment.JpaIntegrationEnvironmentConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatRepositoryJpaImplTests {
    
    private static Chat TEST_CHAT;

    @Autowired
    private JpaChatRepository jpaChatRepository;

    @BeforeAll
    public static void setTestChat() {
        TEST_CHAT = new Chat()
                .setChatId(1L);
    }

    @Test
    @Transactional
    @Rollback
    public void saveTest() {
        Chat chat = jpaChatRepository.save(TEST_CHAT);
        assertNotNull(chat.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void findByIdTest() {
        Chat chat = jpaChatRepository.save(TEST_CHAT);
        Optional<Chat> findChat = jpaChatRepository.findById(chat.getId());
        assertTrue(findChat.isPresent());
        Chat presentChat = findChat.get();
        assertEquals(chat.getId(), presentChat.getId());
        assertEquals(chat.getChatId(), presentChat.getChatId());
    }

    @Test
    @Transactional
    @Rollback
    public void findByChatIdTest() {
        jpaChatRepository.save(TEST_CHAT);
        Chat chat = jpaChatRepository.findByChatId(1L);
        assertNotNull(chat);
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Chat chat = jpaChatRepository.save(TEST_CHAT);
        assertEquals(jpaChatRepository.count(), 1);
        jpaChatRepository.delete(chat);
        assertEquals(jpaChatRepository.count(), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest() {
        Chat chat = jpaChatRepository.save(TEST_CHAT);
        List<Chat> chats = jpaChatRepository.findAll();

        assertEquals(chats.size(), 1);
    }
}
