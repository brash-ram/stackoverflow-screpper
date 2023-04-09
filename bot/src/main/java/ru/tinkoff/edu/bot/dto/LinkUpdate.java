package ru.tinkoff.edu.bot.dto;

import java.net.URI;
import java.util.List;

/**
 * LinkUpdate
 */

public record LinkUpdate (
       Long id,

       URI url,

       String description,

       List<Long> tgChatIds
) {}

