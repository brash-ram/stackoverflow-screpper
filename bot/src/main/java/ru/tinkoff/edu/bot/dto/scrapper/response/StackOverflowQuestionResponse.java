package ru.tinkoff.edu.bot.dto.scrapper.response;

import ru.tinkoff.edu.scrapper.dto.stackoverflow.StackOverflowQuestion;

import java.util.List;

public record StackOverflowQuestionResponse(
        List<StackOverflowQuestion> items
) {
}
