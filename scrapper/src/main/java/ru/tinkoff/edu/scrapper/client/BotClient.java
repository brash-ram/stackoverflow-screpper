package ru.tinkoff.edu.scrapper.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.scrapper.dto.request.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotClient {

    private final WebClient botClient;

    @Value("${default.timeout}")
    private Integer defaultTimeout;

    @Value("${baseUrl.scrapper}")
    private String scrapperUrl;

    public void updateLink(LinkUpdateRequest linkUpdateRequest) {
        botClient.post()
                .uri(scrapperUrl)
                .body(BodyInserters.fromValue(linkUpdateRequest))
                .retrieve();
    }
}
