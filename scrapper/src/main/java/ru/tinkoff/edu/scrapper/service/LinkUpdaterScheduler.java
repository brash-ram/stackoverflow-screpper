package ru.tinkoff.edu.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.dto.LinkData;
import ru.tinkoff.edu.scrapper.client.BotClient;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.scrapper.service.api.ApiService;
import ru.tinkoff.edu.service.LinkParseService;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final ApiService apiService;
    private final BotClient botClient;
    private final LinkService linkService;
    private final LinkParseService linkParseService;

    @Scheduled(fixedDelayString = "#{@delay}")
    public void update() {
        System.out.println("Update");
    }

    public void updateLinks(List<Link> links) {
        for (Link link : links) {
            LinkData linkData = linkParseService.parseLink(link.getUrl());
            String description = apiService.checkUpdate(linkData);
            sendLinkUpdateMessage(new LinkUpdateRequest(
                    link.getId(),
                    link.getUrl(),
                    description,
                    List.of(link.getChat().getChatId())
            ));
            linkService.updateTimeUpdate(link.getId(), new Timestamp(System.currentTimeMillis()));
        }
    }

    private void sendLinkUpdateMessage(LinkUpdateRequest request) {
        botClient.updateLink(request);
    }
}
