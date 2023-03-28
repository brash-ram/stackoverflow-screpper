package ru.tinkoff.edu.scrapper.dto.response;

import java.net.URI;

/**
 * LinkResponse
 */

public record LinkResponse (
        Long id,
        URI url
) {}

