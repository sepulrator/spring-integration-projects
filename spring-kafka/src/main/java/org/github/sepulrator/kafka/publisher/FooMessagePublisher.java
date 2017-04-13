package org.github.sepulrator.kafka.publisher;

import org.github.sepulrator.kafka.message.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by osman on 13.4.2017.
 */
public class FooMessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooMessagePublisher.class);

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    private KafkaTemplate<String, ?> kafkaFooTemplate;

    public void send(Foo foo) {
        Message<?> message = new GenericMessage<>(foo);
        kafkaFooTemplate.send(message);
        LOGGER.info(message.toString() + "  has been sent");
    }

    @Scheduled(fixedRate = 1000,initialDelay = 10)
    public void sendMessage() {
        send(new Foo().setId(counter.incrementAndGet()));
    }


}
