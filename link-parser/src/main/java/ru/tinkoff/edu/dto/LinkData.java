package ru.tinkoff.edu.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.enums.Site;

import java.net.URL;

@AllArgsConstructor
@NoArgsConstructor
public abstract class LinkData {
    private URL url;
    private Site site;
}
