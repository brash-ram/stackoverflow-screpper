package ru.tinkoff.edu.controller;

import openapi.api.TgChatApi;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.edu.dto.request.AddLinkRequest;

public class TgChatController implements TgChatApi {

    @Override
    public ResponseEntity<Void> tgChatIdDelete(Long id) {
        return TgChatApi.super.tgChatIdDelete(id);
    }

    @Override
    public ResponseEntity<Void> tgChatIdPost(Long id) {
        AddLinkRequest request = new AddLinkRequest();
        return TgChatApi.super.tgChatIdPost(id);
    }
}
