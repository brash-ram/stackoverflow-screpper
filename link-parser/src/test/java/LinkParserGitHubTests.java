

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tinkoff.edu.dto.LinkData;
import ru.tinkoff.edu.dto.LinkDataGithub;
import ru.tinkoff.edu.dto.LinkDataStackOverflow;
import ru.tinkoff.edu.enums.Site;
import ru.tinkoff.edu.service.LinkParseService;
import ru.tinkoff.edu.service.parser.LinkParser;
import ru.tinkoff.edu.service.parser.ParserConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParserConfiguration.class, LinkParseService.class })
public class LinkParserGitHubTests {
    @Autowired
    private LinkParseService linkParseService;

    @Test
    public void parseGithubLink(){
        URL link;
        try {
            link = new URL("https://github.com/brash-ram/tinkoff-screpper");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        LinkData expected = new LinkDataGithub(link, Site.GITHUB, "brash-ram", "tinkoff-screpper");
        assertEquals(expected, linkParseService.parseLink(link));
    }

    @Test
    public void parseValidStackOverflowLink(){
        URL link;
        try {
            link = new URL("https://stackoverflow.com/questions/57772342/how-to-add-a-bean-in-springboottest");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        LinkData expected = new LinkDataStackOverflow(link, Site.STACK_OVERFLOW, 57772342L);
        assertEquals(expected, linkParseService.parseLink(link));
    }

    @Test
    public void parseInvalidGitHubLink(){
        URL link;
        try {
            link = new URL("https://github.com/features");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        assertNull(linkParseService.parseLink(link));
    }

    @Test
    public void parseInvalidStackOverflowLink(){
        URL link;
        try {
            link = new URL("https://stackoverflow.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        assertNull(linkParseService.parseLink(link));
    }
}
