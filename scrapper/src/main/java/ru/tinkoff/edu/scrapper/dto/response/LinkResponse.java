package ru.tinkoff.edu.scrapper.dto.response;

import lombok.AllArgsConstructor;

import java.net.URI;

/**
 * LinkResponse
 */

public record LinkResponse (
        Long id,
        URI url
) {}

