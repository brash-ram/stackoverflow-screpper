package ru.tinkoff.edu.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Value("${baseUrl.stackOverflow}")
    private String stackOverflowBaseUrl;

    @Value("${baseUrl.github}")
    private String gitHubBaseUrl;

    @Bean("gitHubWebClient")
    public WebClient gitHubWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(gitHubBaseUrl)
                .build();
    }

    @Bean("stackOverflowWebClient")
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(stackOverflowBaseUrl)
                .build();
    }

    @Bean("botWebClient")
    public WebClient botWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
