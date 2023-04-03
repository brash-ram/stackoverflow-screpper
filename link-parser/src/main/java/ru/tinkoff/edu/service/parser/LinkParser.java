package ru.tinkoff.edu.service.parser;

import lombok.Setter;
import ru.tinkoff.edu.dto.LinkData;

import java.net.URL;

@Setter
public abstract class LinkParser {
    protected LinkParser nextParser;

    public abstract LinkData parseUrl(URL url);
}
