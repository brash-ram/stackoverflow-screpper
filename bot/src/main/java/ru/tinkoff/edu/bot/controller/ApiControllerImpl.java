package ru.tinkoff.edu.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.bot.dto.LinkUpdate;

@RestController
public class ApiControllerImpl implements ApiController {
    @Override
    public ResponseEntity<Void> updatesPost(LinkUpdate linkUpdate) {

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleExceptions() {
        return null;
    }
}
