package ru.tinkoff.edu.scrapper.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Chat {
    private Long id;
    private Long chatId;
    private Set<Link> links;
}
