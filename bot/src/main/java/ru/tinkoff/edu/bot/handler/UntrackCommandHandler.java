package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.bot.client.ScrapperClient;
import ru.tinkoff.edu.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.bot.dto.scrapper.request.RemoveLinkRequest;
import ru.tinkoff.edu.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.bot.tg.Bot;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

import java.net.URI;
import java.util.Optional;

@Component
@Slf4j
public class UntrackCommandHandler extends MessageHandler{

    private final ScrapperClient scrapperClient;

    public UntrackCommandHandler(Bot bot, ScrapperClient scrapperClient) {
        super(bot);
        this.scrapperClient = scrapperClient;
    }

    @Override
    public void handleMessage(Update update) {
        Message message = update.message();
        if (message.text().equals("/untrack")) {
            try {
                Optional<LinkResponse> response = scrapperClient.deleteLink(new RemoveLinkRequest(new URI("http://localhost")), 1L);
                response.ifPresent(linkResponse -> log.info(linkResponse.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            bot.send(new SendMessageAdapter(message.chat().id(), DEFAULT_MASSAGE + "untrack")
                    .getSendMessage());
        } else {
            nextHandler.handleMessage(update);
        }
    }
}
