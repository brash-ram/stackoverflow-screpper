package ru.tinkoff.edu.scrapper.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
     public ResponseEntity<LinkResponse> linksDelete(@NotNull @RequestHeader(value = "Tg-Chat-Id") Long tgChatId,
                                                     @RequestBody RemoveLinkRequest removeLinkRequest) {
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/links",
            produces = { "application/json" }
    )
    public ResponseEntity<ListLinksResponse> linksGet(@NotNull @RequestHeader(value = "Tg-Chat-Id") Long tgChatId) {
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/links",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<LinkResponse> linksPost(@NotNull @RequestHeader(value = "Tg-Chat-Id") Long tgChatId,
                                                  @RequestBody AddLinkRequest addLinkRequest) {
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/tg-chat/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> tgChatIdDelete(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/tg-chat/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> tgChatIdPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
