package ru.tinkoff.edu.scrapper.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.scrapper.service.ChatService;
import ru.tinkoff.edu.scrapper.service.LinkService;
import ru.tinkoff.edu.scrapper.utils.DtoMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiControllerImpl implements ApiController {

    private final LinkService jdbcLinkService;
    private final ChatService jdbcChatService;
    private final DtoMapper dtoMapper;

    @Override
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/links",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
     public ResponseEntity<LinkResponse> linksDelete(@NotNull @RequestHeader(value = "Tg-Chat-Id") Long tgChatId,
                                                     @RequestBody RemoveLinkRequest removeLinkRequest) {
        Link link = jdbcLinkService.remove(tgChatId, removeLinkRequest.link());
        return ResponseEntity.ok(dtoMapper.convertLinkToLinkResponse(link));
    }

    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/links",
            produces = { "application/json" }
    ) 
    public ResponseEntity<ListLinksResponse> linksGet(@NotNull @RequestHeader(value = "Tg-Chat-Id") Long tgChatId) {
        List<Link> link = jdbcLinkService.listAll(tgChatId);
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
        Link link = jdbcLinkService.add(tgChatId, addLinkRequest.link());
        return ResponseEntity.ok(dtoMapper.convertLinkToLinkResponse(link));
    }

    @Override
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/tg-chat/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> tgChatIdDelete(@PathVariable Long id) {
        jdbcChatService.unregister(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/tg-chat/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> tgChatIdPost(@PathVariable("id") Long id) {
        jdbcChatService.register(id);
        return ResponseEntity.ok().build();
    }
}
