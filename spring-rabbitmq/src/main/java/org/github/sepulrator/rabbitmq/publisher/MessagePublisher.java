package org.github.sepulrator.rabbitmq.publisher;

import org.github.sepulrator.rabbitmq.message.Bar;
import org.github.sepulrator.rabbitmq.message.Foo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by osman on 2.4.2017.
 */
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private final AtomicInteger counter = new AtomicInteger();

    @Scheduled(fixedRate = 6000,initialDelay = 10)
    public void sendMessage() {
        //rabbitTemplate.convertAndSend(new Foo().setId(counter.incrementAndGet()));
        //rabbitTemplate.convertAndSend(ConsumerConfig.topicExchangeName,"fanout-key",new Foo().setId(counter.incrementAndGet()));
        //rabbitTemplate.convertAndSend(ConsumerConfig.directExchangeName,"fan",new Foo().setId(counter.incrementAndGet()));
        rabbitTemplate.convertAndSend("foo",new Foo().setId(counter.incrementAndGet()));
        rabbitTemplate.convertAndSend("bar",new Bar().setId(counter.get()));
    }


}
