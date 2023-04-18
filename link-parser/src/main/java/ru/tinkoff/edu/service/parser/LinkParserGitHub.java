package ru.tinkoff.edu.service.parser;

import ru.tinkoff.edu.dto.LinkData;
import ru.tinkoff.edu.dto.LinkDataGithub;
import ru.tinkoff.edu.enums.Site;

import java.net.URI;
import java.net.URL;

final class LinkParserGitHub extends LinkParser{

    @Override
    public LinkData parseUrl(URI url) {
        if (url.getHost().equals(Site.GITHUB.getHost())) {
            String[] githubPath = url.getPath().replaceFirst("/", "").split("/");
            if (githubPath.length == 2) {
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
