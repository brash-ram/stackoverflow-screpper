package ru.tinkoff.edu.linkParser.service.parser;

import ru.tinkoff.edu.linkParser.dto.LinkData;
import ru.tinkoff.edu.linkParser.dto.LinkDataStackOverflow;
import ru.tinkoff.edu.linkParser.enums.Site;

import java.net.URI;

final class LinkParserStackOverflow extends LinkParser {

    @Override
    public LinkData parseUrl(URI url) {
        if (url.getHost().equals(Site.STACK_OVERFLOW.getHost())) {
            String[] stackOverflowPath = url.getPath().replaceFirst("/", "").split("/");
            String QUESTIONS_PATH = "questions";

            if (stackOverflowPath.length != 0 && stackOverflowPath[0].equals(QUESTIONS_PATH)) {
                return new LinkDataStackOverflow(
                        url,
                        Site.STACK_OVERFLOW,
                        Long.valueOf(stackOverflowPath[1])
                );
            } else {
                return null;
            }
        } else if (nextParser != null) {
            return nextParser.parseUrl(url);
        }
        return null;
    }
}
