package ru.tinkoff.edu.scrapper.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {IntegrationEnvironment.IntegrationEnvironmentConfiguration.class})
public class IntegrationTests extends IntegrationEnvironment {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void beforeAllTest() {
        IntegrationEnvironment.runMigration();
    }

    @Test
    public void databaseTest() {
        Long id = 1L;
        Long chatId = 123L;
        jdbcTemplate.update("INSERT INTO chats VALUES (?, ?)",  id, chatId);
        var result = jdbcTemplate.queryForList("SELECT * FROM chats");
        assertNotNull(result);
        assertEquals(result.get(0).get("id"), id);
        assertEquals(result.get(0).get("chat_id"), chatId);
    }
}
