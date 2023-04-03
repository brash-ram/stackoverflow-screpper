package ru.tinkoff.edu.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

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

