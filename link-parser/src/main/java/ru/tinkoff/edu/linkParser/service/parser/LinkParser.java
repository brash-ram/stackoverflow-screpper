package ru.tinkoff.edu.linkParser.service.parser;

import lombok.Setter;
import ru.tinkoff.edu.linkParser.dto.LinkData;

import java.net.URI;

@Setter
public abstract class LinkParser {
    protected LinkParser nextParser;

    public abstract LinkData parseUrl(URI url);
}
