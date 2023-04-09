package ru.tinkoff.edu.bot.dto.scrapper.response;

import java.net.URI;

/**
 * LinkResponse
 */

public record LinkResponse (
        Long id,
        URI url
) {}

