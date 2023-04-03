package ru.tinkoff.edu.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean("delay")
    public long getDelay(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }

}
