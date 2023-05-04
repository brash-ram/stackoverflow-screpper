package ru.tinkoff.edu.linkParser.dto;

import lombok.Data;
import ru.tinkoff.edu.linkParser.enums.Site;

import java.net.URI;

@Data
public final class LinkDataGithub extends LinkData{
    private String user;
    private String repository;

    public LinkDataGithub(URI url, Site site, String user, String repository) {
        super(url, site);
        this.user = user;
        this.repository = repository;
    }
}
