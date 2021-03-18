package com.example.racing.messages.impl;

import com.example.racing.config.Configuration;
import com.example.racing.messages.EventPublisher;
import com.example.racing.model.Report;
import com.example.racing.model.internal.Event;
import com.example.racing.model.internal.Operation;
import com.example.racing.model.Team;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
@RabbitListener
public class EventPublisherImpl implements EventPublisher {
    private final String EXCHANGE_TOPIC = Configuration.TOPIC_EXCHANGE_NAME;
    private final String KEY = Configuration.KEY;

    private final RabbitTemplate rabbitTemplate;

    private final FakeValuesService fakeValuesService;

    public EventPublisherImpl(RabbitTemplate rabbitTemplate, FakeValuesService fakeValuesService) {
        this.rabbitTemplate = rabbitTemplate;
        this.fakeValuesService = fakeValuesService;
    }


    @Override
    @Async
    public void init() {
        final int size = 10;
        List<Report> reports = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Report report = new Report();
            Faker faker = new Faker();
            report.setCountry(faker.address().country());
            report.setMoneyReward(faker.number().randomDigit());
            report.setPosition(faker.number().numberBetween(0, 10));
            report.setName(faker.name().firstName());
            report.setDate(LocalDateTime.ofInstant(faker.date().birthday().toInstant(), TimeZone.getDefault().toZoneId()));
            reports.add(report);
        }
        for(int i = 0; i < size; i++) {
            Event event = new Event(Operation.CREATE);
            event.setOperateOn(reports.get(i));
            rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, KEY, event);
        }
    }
}
