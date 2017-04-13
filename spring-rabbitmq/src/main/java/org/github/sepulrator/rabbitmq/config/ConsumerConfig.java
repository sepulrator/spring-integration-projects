package org.github.sepulrator.rabbitmq.config;

import org.github.sepulrator.rabbitmq.listener.BarMessageListener;
import org.github.sepulrator.rabbitmq.listener.FooMessageListener;
import org.github.sepulrator.rabbitmq.listener.exception.MessageListenerExceptionHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

/**
 * Created by osman on 1.4.2017.
 */
@Configuration
public class ConsumerConfig {
    public static final String fooFanoutExchangeName = "foo-fanout-exchange";
    public static final String barFanoutExchangeName = "bar-fanout-exchange";
    public static final String directExchangeName = "direct-exchange";
    public static final String fooQueueName = "foo-queue";
    public static final String barQueueName = "bar-queue";

    @Bean
    Queue fooQueue() {
        return new Queue(fooQueueName);
    }

    @Bean
    Queue barQueue() {
        return new Queue(barQueueName);
    }

    @Bean
    FanoutExchange fooFanoutExchange() {
        return new FanoutExchange(fooFanoutExchangeName);
    }

    @Bean
    FanoutExchange barFanoutExchange() {
        return new FanoutExchange(barFanoutExchangeName);
    }

    @Bean
    Binding bindingFooQueue(FanoutExchange fooFanoutExchange) {
        return BindingBuilder.bind(fooQueue()).to(fooFanoutExchange());
    }

    @Bean
    Binding bindingBarQueue(FanoutExchange barFanoutExchange) {
        return BindingBuilder.bind(barQueue()).to(barFanoutExchange());
    }

    @Bean
    Binding bindingFooExchange() {
        Binding.DestinationType destinationType = Binding.DestinationType.EXCHANGE;
        return new Binding(fooFanoutExchangeName,destinationType,directExchangeName,"foo",null);
    }

    @Bean
    Binding bindingBarExchange() {
        Binding.DestinationType destinationType = Binding.DestinationType.EXCHANGE;
        return new Binding(barFanoutExchangeName,destinationType,directExchangeName,"bar",null);
    }



    @Bean
    SimpleMessageListenerContainer fooContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(fooQueue());
        container.setConcurrentConsumers(4);
        container.setMaxConcurrentConsumers(10);
        container.setMessageConverter(jsonToMessageConverter());
        container.setMessageListener(fooMessageListener());
        //container.setErrorHandler(errorHandler());
        return container;
    }

    @Bean
    SimpleMessageListenerContainer barContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(fooQueue());
        container.setConcurrentConsumers(4);
        container.setMaxConcurrentConsumers(10);
        container.setMessageConverter(jsonToMessageConverter());
        container.setMessageListener(barMessageListener());
        //container.setErrorHandler(errorHandler());
        return container;
    }


    /*@Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MessageListenerExceptionHandler());
    }*/

    @Bean
    public JsonMessageConverter  jsonToMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    public FooMessageListener fooMessageListener() {
        return new FooMessageListener();
    }

    @Bean
    public BarMessageListener barMessageListener() {
        return new BarMessageListener();
    }


}
