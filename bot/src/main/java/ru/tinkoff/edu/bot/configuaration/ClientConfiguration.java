package ru.tinkoff.edu.bot.configuaration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Value("${baseUrl.scrapper}")
    private String scrapperBaseUrl;

    @Bean("scrapperWebClient")
    public WebClient scrapperWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(scrapperBaseUrl)
                .build();
    }
}
