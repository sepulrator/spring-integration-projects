package org.github.sepulrator.kafka.consumer;

import org.github.sepulrator.kafka.message.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.concurrent.CountDownLatch;

/**
 * Created by osman on 9.4.2017.
 */
public class MessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private CountDownLatch fooLatch = new CountDownLatch(1);
    private CountDownLatch barLatch = new CountDownLatch(1);

    @KafkaListener(id = "foo0",
            group = "FooConsumer",
            containerFactory = "kafkaFooListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "${kafka.topic.foo}", partitions = { "0"})} )
    public void receiveFooMessagePart0(Foo message) {
        LOGGER.info("received foo message with id '{}'", message.getId());
        fooLatch.countDown();
    }

    @KafkaListener(id = "foo1",
            group = "FooConsumer",
            containerFactory = "kafkaFooListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "${kafka.topic.foo}", partitions = { "1"})} )
    public void receiveFooMessagePart1(Foo message) {
        LOGGER.info("received foo message with id '{}'", message.getId());
        fooLatch.countDown();
    }

    @KafkaListener(id = "foo2",
            group = "FooConsumer",
            containerFactory = "kafkaFooListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "${kafka.topic.foo}", partitions = { "2"})} )
    public void receiveFooMessagePart2(Foo message) {
        LOGGER.info("received foo message with id '{}'", message.getId());
        fooLatch.countDown();
    }

    @KafkaListener(id = "bar", topics = "${kafka.topic.bar}", containerFactory = "kafkaBarListenerContainerFactory")
    public void receiveBarMessage(String message) {
        LOGGER.info("received bar message='{}'", message);
        barLatch.countDown();
    }

    public CountDownLatch getFooLatch() {
        return fooLatch;
    }
    public CountDownLatch getBarLatch() {
        return barLatch;
    }

}
