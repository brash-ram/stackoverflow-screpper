package ru.tinkoff.edu.service;

import lombok.Setter;
import ru.tinkoff.edu.dto.LinkData;

import java.net.URL;

@Setter
abstract class LinkParser {
    protected LinkParser nextParser;

    public abstract LinkData parseUrl(URL url);
}
