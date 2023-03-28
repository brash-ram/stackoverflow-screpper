package ru.tinkoff.edu.service;

import ru.tinkoff.edu.dto.LinkData;
import ru.tinkoff.edu.dto.LinkDataGithub;
import ru.tinkoff.edu.enums.Site;

import java.net.URL;

final class LinkParserGitHub extends LinkParser{

    private static LinkParserGitHub instance;

    public static LinkParserGitHub getInstance() {
        if (instance == null) {
            synchronized (LinkParserGitHub.class) {
                instance = new LinkParserGitHub();
            }
        }
        return instance;
    }

    @Override
    public LinkData parseUrl(URL url) {
        if (url.getHost().equals(Site.GITHUB.getHost())) {
            String[] githubPath = url.getPath().replaceFirst("/", "").split("/");
            if (githubPath.length != 0) {
                return new LinkDataGithub(
                        url,
                        Site.GITHUB,
                        githubPath[0],
                        githubPath[1]
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
