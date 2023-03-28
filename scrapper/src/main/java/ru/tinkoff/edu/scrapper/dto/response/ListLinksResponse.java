package ru.tinkoff.edu.scrapper.dto.response;

import jakarta.validation.Valid;

import java.util.List;

/**
 * ListLinksResponse
 */


public record ListLinksResponse (
        @Valid
        List<LinkResponse> links,

        Integer size
) {}

