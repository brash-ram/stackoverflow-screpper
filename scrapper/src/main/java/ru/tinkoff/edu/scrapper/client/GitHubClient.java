package ru.tinkoff.edu.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.scrapper.dto.response.GitHubRepositoryInfoResponse;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class GitHubClient {
    private final WebClient gitHubWebClient;
    @Value("${default.timeout}")
    private Integer defaultTimeout;

    public GitHubRepositoryInfoResponse getGitHubRepositoryInfo(String username, String repositoryName) {
        return gitHubWebClient.get()
                .uri("/repos/{username}/{repositoryName}", username, repositoryName)
                .retrieve()
                .bodyToMono(GitHubRepositoryInfoResponse.class)
                .timeout(Duration.ofSeconds(defaultTimeout))
                .blockOptional()
                .orElse(null);
    }
}
