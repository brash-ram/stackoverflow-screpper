package ru.tinkoff.edu.scrapper.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.scrapper.dto.response.ListLinksResponse;

@RestController
public class ApiControllerImpl implements ApiController {
    @Override
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/links",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
     public ResponseEntity<LinkResponse> linksDelete(@NotNull @RequestHeader(value = "Tg-Chat-Id", required = true) Long tgChatId,
                                                     @Valid @RequestBody RemoveLinkRequest removeLinkRequest) {
        throw new RuntimeException("Test exception");
    }

    @Override
    public ResponseEntity<ListLinksResponse> linksGet(Long tgChatId) {
        return null;
    }

    @Override
    public ResponseEntity<LinkResponse> linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> tgChatIdDelete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> tgChatIdPost(Long id) {
        return null;
    }
}
