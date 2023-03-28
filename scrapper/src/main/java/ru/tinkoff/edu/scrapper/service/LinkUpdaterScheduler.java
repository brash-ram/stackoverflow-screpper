package ru.tinkoff.edu.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    @Scheduled(fixedDelayString = "#{@delay}")
    public void update() {
        System.out.println("Update");
    }

}
