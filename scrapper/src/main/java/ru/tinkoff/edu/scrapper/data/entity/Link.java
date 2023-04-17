package ru.tinkoff.edu.scrapper.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.net.URI;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Link {
    private Long id;
    private URI url;
    private Chat chat;
    private Timestamp lastUpdate;
}
