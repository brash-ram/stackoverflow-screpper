package ru.tinkoff.edu.scrapper.repository.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.data.respository.LinkRepository;
import ru.tinkoff.edu.scrapper.data.respository.jdbcImpl.LinkRepositoryJdbcImpl;
import ru.tinkoff.edu.scrapper.environment.IntegrationEnvironment;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = {IntegrationEnvironment.IntegrationEnvironmentConfiguration.class, LinkRepositoryJdbcImpl.class})
public class LinkRepositoryJdbcImplTests {
    private static Link TEST_LINK;

    @Autowired
    private LinkRepository linkRepository;

    @BeforeAll
    public static void setTestLink() throws URISyntaxException {
        TEST_LINK = new Link()
                .setUrl(new URI("http://localhost:8080"))
                .setLastUpdate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    @Transactional
    @Rollback
    public void addTest() {
        Link link = linkRepository.save(TEST_LINK);
        assertNotNull(link.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest() {
        Link link = linkRepository.save(TEST_LINK);
        linkRepository.remove(link.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        linkRepository.save(TEST_LINK);
        linkRepository.save(TEST_LINK);

        assertEquals(linkRepository.findAll().size(), 2);
    }

    @Test
    @Transactional
    @Rollback
    public void updateLastUpdateAll() {
        Link link = linkRepository.save(TEST_LINK);
        Timestamp timestamp = new Timestamp(100000000L);
        linkRepository.updateLastUpdate(link.getId(), timestamp);
        link = linkRepository.findAll().get(0);

        assertEquals(link.getLastUpdate(), timestamp);
    }

}
