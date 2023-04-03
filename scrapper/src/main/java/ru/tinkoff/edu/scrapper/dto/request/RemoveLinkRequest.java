package ru.tinkoff.edu.scrapper.dto.request;

import java.net.URI;

/**
 * RemoveLinkRequest
 */
public record RemoveLinkRequest (
        URI link
) {}

