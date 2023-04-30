package ru.tinkoff.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.dto.LinkData;
import ru.tinkoff.edu.service.parser.LinkParser;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LinkParseService {
    private final LinkParser linkParser;

    public LinkData parseLink(URI url) {
        return linkParser.parseUrl(url);
    }

}
