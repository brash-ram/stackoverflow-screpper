package ru.tinkoff.edu.service;

import ru.tinkoff.edu.dto.LinkData;

import java.net.URL;

public class LinkParseService {
    private final LinkParser linkParser;

    public LinkParseService() {
        this.linkParser = LinkParserStackOverflow.getInstance();
        this.linkParser.setNextParser(LinkParserGitHub.getInstance());
    }

    public LinkData parseLink(URL url) {
        return linkParser.parseUrl(url);
    }

}
