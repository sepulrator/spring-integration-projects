package org.github.sepulrator.rabbitmq.config;

import org.github.sepulrator.rabbitmq.publisher.MessagePublisher;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by osman on 1.4.2017.
 */
@Configuration
public class ProducerConfig {
    private static final String directExchangeName = "direct-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageToJsonConverter());
        template.setExchange(ConsumerConfig.directExchangeName);
        return template;
    }

    @Bean
    DirectExchange topicExchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    public MessageConverter messageToJsonConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }


    @Bean
    public MessagePublisher messagePublisher() {
        return new MessagePublisher();
    }

}
