package ru.tinkoff.edu.bot.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.bot.dto.scrapper.response.LinkResponse;

import java.time.Duration;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ScrapperClient {

    private final WebClient scrapperWebClient;

    @Value("${default.timeout}")
    private Integer defaultTimeout;

    public void createTgChat(AddLinkRequest addLinkRequest) {
        WebClient.ResponseSpec responseSpec = scrapperWebClient.post()
                .uri("/links")
                .body(BodyInserters.fromValue(addLinkRequest))
                .retrieve();

        if (Objects.requireNonNull(responseSpec.toBodilessEntity().block())
                .getStatusCode().equals(HttpStatus.OK)) {
            LinkResponse linkResponse = responseSpec
                    .bodyToMono(LinkResponse.class)
                    .timeout(Duration.ofSeconds(defaultTimeout))
                    .block();
            System.out.println("ok");
        } else if (Objects.requireNonNull(responseSpec.toBodilessEntity().block())
                .getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            ApiErrorResponse apiErrorResponse = responseSpec
                    .bodyToMono(ApiErrorResponse.class)
                    .timeout(Duration.ofSeconds(defaultTimeout))
                    .block();
            System.out.println("bad");
        }
    }
}
