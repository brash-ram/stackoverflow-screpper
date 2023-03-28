package ru.tinkoff.edu.scrapper.dto.response;

import ru.tinkoff.edu.scrapper.dto.stackoverflow.StackOverflowQuestion;

import java.util.List;

public record StackOverflowQuestionResponse(
        List<StackOverflowQuestion> items
) {
}
