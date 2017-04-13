package org.github.sepulrator.rabbitmq.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by osman on 1.4.2017.
 */

@SpringBootApplication
@Import({ConsumerConfig.class, ProducerConfig.class})
@ComponentScan(basePackages = "org.github.sepulrator")
@EnableScheduling
public class Application {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
