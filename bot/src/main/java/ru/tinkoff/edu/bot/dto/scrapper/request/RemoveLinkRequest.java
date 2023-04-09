package ru.tinkoff.edu.bot.dto.scrapper.request;

import java.net.URI;

/**
 * RemoveLinkRequest
 */
public record RemoveLinkRequest (
        URI link
) {}

