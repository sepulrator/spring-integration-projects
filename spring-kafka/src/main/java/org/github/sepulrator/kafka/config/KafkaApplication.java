package org.github.sepulrator.kafka.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by osman on 8.4.2017.
 */
@SpringBootApplication(exclude=KafkaAutoConfiguration.class)
@EnableScheduling
public class KafkaApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaApplication.class, args);
    }

}
