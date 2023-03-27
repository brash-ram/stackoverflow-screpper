package ru.tinkoff.edu.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.Objects;

/**
 * RemoveLinkRequest
 */
public record RemoveLinkRequest (
        URI link
) {}

