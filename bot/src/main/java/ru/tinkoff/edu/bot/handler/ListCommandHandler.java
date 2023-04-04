package ru.tinkoff.edu.bot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.bot.client.ScrapperClient;
import ru.tinkoff.edu.bot.dto.scrapper.response.ListLinksResponse;
import ru.tinkoff.edu.bot.tg.Bot;
import ru.tinkoff.edu.bot.tg.SendMessageAdapter;

import java.util.Optional;

@Component
@Slf4j
public class ListCommandHandler extends MessageHandler{
    private final ScrapperClient scrapperClient;

    public ListCommandHandler(Bot bot, ScrapperClient scrapperClient) {
        super(bot);
        this.scrapperClient = scrapperClient;
    }

    @Override
    public void handleMessage(Update update) {
        Message message = update.message();
        if (message.text().equals("/list")) {
            try {
                Optional<ListLinksResponse> response = scrapperClient.getLinks(1L);
                response.ifPresent(linkResponse -> log.info(linkResponse.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            bot.send(new SendMessageAdapter(message.chat().id(), DEFAULT_MASSAGE + "list")
                    .getSendMessage());
        } else {
            nextHandler.handleMessage(update);
        }
    }
}
