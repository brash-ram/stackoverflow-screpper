package ru.tinkoff.edu.scrapper.repository.jpa;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
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
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;
import ru.tinkoff.edu.scrapper.service.ChatService;
import ru.tinkoff.edu.scrapper.service.LinkService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(excludeAutoConfiguration = LiquibaseAutoConfiguration.class)
@Import(IntegrationEnvironment.JpaIntegrationEnvironmentConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaLinkServiceTests {

    @Autowired
    private LinkService jpaLinkService;

    @Autowired
    private ChatService jpaChatService;

    private static Link TEST_LINK;

    private static Chat TEST_CHAT;

    @BeforeEach
    public void setTestLink() throws URISyntaxException {
        TEST_LINK = new Link()
                .setUrl(new URI("http://localhost:8080"))
                .setLastUpdate(new Timestamp(400000L));
        TEST_CHAT = new Chat()
                .setChatId(1L);
    }

    @Test
    @Transactional
    @Rollback
    public void updateTimeUpdateTest() {
        Link link = jpaLinkService.add(TEST_CHAT.getChatId(), TEST_LINK.getUrl());
        jpaLinkService.updateTimeUpdate(link.getId(), TEST_LINK.getLastUpdate());
        assertEquals(jpaLinkService.getAll().get(0).getLastUpdate(), TEST_LINK.getLastUpdate());
    }

    @Test
    @Transactional
    @Rollback
    public void addTest() {
        jpaChatService.register(TEST_CHAT.getChatId());
        Link link = jpaLinkService.add(TEST_CHAT.getChatId(), TEST_LINK.getUrl());
        assertNotNull(link.getId());
    }

}
