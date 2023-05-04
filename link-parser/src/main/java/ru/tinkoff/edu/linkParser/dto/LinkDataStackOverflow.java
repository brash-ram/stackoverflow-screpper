package ru.tinkoff.edu.linkParser.dto;

import lombok.Data;
import ru.tinkoff.edu.linkParser.enums.Site;

import java.net.URI;

@Data
public final class LinkDataStackOverflow extends LinkData{
    private Long id;

    public LinkDataStackOverflow(URI url, Site site, Long id) {
        super(url, site);
        this.id = id;
    }
}
