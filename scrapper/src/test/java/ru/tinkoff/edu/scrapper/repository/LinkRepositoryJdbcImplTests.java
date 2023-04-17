package ru.tinkoff.edu.scrapper.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.impl.LinkRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {IntegrationEnvironment.IntegrationEnvironmentConfiguration.class, LinkRepositoryJdbcImpl.class})
public class LinkRepositoryJdbcImplTests extends IntegrationEnvironment  {
    private static Link TEST_LINK;

    @Autowired
    private LinkRepositoryJdbcImpl linkRepositoryJdbcImpl;

    @BeforeAll
    public static void setTestLink() throws URISyntaxException {
        IntegrationEnvironment.runMigration();
        TEST_LINK = new Link()
                .setUrl(new URI("http://localhost:8080"))
                .setLastUpdate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    @Transactional
    @Rollback
    public void addTest() {
        Link link = linkRepositoryJdbcImpl.save(TEST_LINK);
        assertNotNull(link.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Link link = linkRepositoryJdbcImpl.save(TEST_LINK);
        linkRepositoryJdbcImpl.remove(link.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        linkRepositoryJdbcImpl.save(TEST_LINK);
        linkRepositoryJdbcImpl.save(TEST_LINK);

        assertEquals(linkRepositoryJdbcImpl.findAll().size(), 2);
    }

}
