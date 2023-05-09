package ru.tinkoff.edu.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.scrapper.configuration.RabbitMQConfig;
import ru.tinkoff.edu.scrapper.dto.request.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;

    public void send(LinkUpdateRequest linkUpdate) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.exchange(), rabbitMQConfig.queue(), linkUpdate);
    }

}
