package ru.tinkoff.edu.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.scrapper.client.BotClient;
import ru.tinkoff.edu.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.scrapper.dto.request.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
public class LinkUpdateService {

    private final ScrapperQueueProducer scrapperQueueProducer;
    private final BotClient botClient;
    private final ApplicationConfig applicationConfig;

    public void sendLinkUpdate(LinkUpdateRequest updateRequest) {
        if (applicationConfig.useQueue()) {
            scrapperQueueProducer.send(updateRequest);
        } else {
            botClient.updateLink(updateRequest);
        }
    }

}
