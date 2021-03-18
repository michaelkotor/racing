package com.example.racing.config;

import com.example.racing.messages.ReceiverEvent;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@org.springframework.context.annotation.Configuration
public class Configuration {
    public static final  String TOPIC_EXCHANGE_NAME = "event-topic";
    public static final String QUEUE_NAME = "event-queue";
    public static final String KEY = "K";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false, false, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(KEY);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ReceiverEvent receiverEvent) {
        return new MessageListenerAdapter(receiverEvent, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public FakeValuesService fakeValuesService() {
        return new FakeValuesService(
                new Locale("en-GB"), new RandomService());
    }

}
