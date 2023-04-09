package ru.tinkoff.edu.bot.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.bot.dto.scrapper.request.RemoveLinkRequest;
import ru.tinkoff.edu.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.bot.dto.scrapper.response.ListLinksResponse;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScrapperClient {

    private final WebClient scrapperWebClient;

    @Value("${default.timeout}")
    private Integer defaultTimeout;

    @Value("${baseUrl.scrapper}")
    private String scrapperUrl;

    public Optional<LinkResponse> addLink(AddLinkRequest addLinkRequest, Long id) {
        return scrapperWebClient.post()
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .body(BodyInserters.fromValue(addLinkRequest))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    return Mono.empty();
                })
                .blockOptional();
    }

    public Optional<LinkResponse> deleteLink(RemoveLinkRequest removeLinkRequest, Long id) {
        return scrapperWebClient.post()
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .body(BodyInserters.fromValue(removeLinkRequest))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    return Mono.empty();
                })
                .blockOptional();
    }

    public Optional<ListLinksResponse> getLinks(Long id) {
        return scrapperWebClient.get()
                .uri("/links")
                .header("Tg-Chat-Id", id.toString())
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    return Mono.empty();
                })
                .blockOptional();
    }

    public Optional<String> addChat(Long id) {
        return scrapperWebClient.post()
                .uri("/tg-chat/" + id.toString())
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    return Mono.empty();
                })
                .blockOptional();
    }

    public Optional<String> deleteChat(Long id) {
        return scrapperWebClient.delete()
                .uri("/tg-chat/" + id.toString())
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    return Mono.empty();
                })
                .blockOptional();
    }
}
