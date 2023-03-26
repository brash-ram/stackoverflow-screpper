package ru.tinkoff.edu.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.scrapper.dto.RemoveLinkRequest;

@RestController
public class ApiControllerImpl implements ApiController {
    @Override
    public ResponseEntity<LinkResponse> linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return null;
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
