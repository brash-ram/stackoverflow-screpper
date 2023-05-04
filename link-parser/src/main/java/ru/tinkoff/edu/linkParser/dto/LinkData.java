package ru.tinkoff.edu.linkParser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.linkParser.enums.Site;

import java.net.URI;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class LinkData {
    private URI url;
    private Site site;
}
