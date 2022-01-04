package ru.vlpetko.metroservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vlpetko.metroservice.service.client.MosDataApiClient;

@Configuration
public class DataBaseConfig {

    @Bean
    CommandLineRunner initDataBase(MosDataApiClient client) {
        return args -> {
            client.getDataFromOpenSource();
        };
    }
}
