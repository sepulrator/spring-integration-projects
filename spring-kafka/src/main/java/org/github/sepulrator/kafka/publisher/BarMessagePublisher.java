package org.github.sepulrator.kafka.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by osman on 9.4.2017.
 */
public class BarMessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarMessagePublisher.class);

    private final AtomicInteger counter = new AtomicInteger();

    @Value("${kafka.topic.bar}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaBarTemplate;

    public void send(String topic, String message) {
        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        ListenableFuture<SendResult<String, String>> future = kafkaBarTemplate.send(topic, message);

        // you can register a callback with the listener to receive the result
        // of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("sent message='{}' with offset={}", message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("unable to send message='{}'", message, ex);
            }
        });

        // alternatively, to block the sending thread, to await the result,
        // invoke the future's get() method

    }

    @Scheduled(fixedRate = 6000,initialDelay = 10)
    public void sendMessage() {
        send(topic,String.valueOf(counter.incrementAndGet()) + ". bar");
    }

}
