package ru.tinkoff.edu.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ApiErrorResponse
 */

public record ApiErrorResponse (
        String description,

        String code,

        String exceptionName,

        String exceptionMessage,

        @Valid
        List<String> stacktrace
) { }