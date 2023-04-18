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

    private final WebClient botWebClient;

    @Value("${default.timeout}")
    private Integer defaultTimeout;

    @Value("${baseUrl.bot}")
    private String botUrl;

    public void updateLink(LinkUpdateRequest linkUpdateRequest) {
        botWebClient.post()
                .uri(botUrl + "/updates")
                .body(BodyInserters.fromValue(linkUpdateRequest))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
