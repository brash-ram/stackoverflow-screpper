package ru.tinkoff.edu.dto;

import lombok.Data;
import ru.tinkoff.edu.enums.Site;

import java.net.URL;

@Data
public final class LinkDataGithub extends LinkData{
    private String user;
    private String repository;

    public LinkDataGithub(URL url, Site site, String user, String repository) {
        super(url, site);
        this.user = user;
        this.repository = repository;
    }
}
