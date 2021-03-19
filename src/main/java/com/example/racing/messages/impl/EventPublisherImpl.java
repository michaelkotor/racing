package com.example.racing.messages.impl;

import com.example.racing.config.Configuration;
import com.example.racing.messages.EventPublisher;
import com.example.racing.model.Pilot;
import com.example.racing.model.Report;
import com.example.racing.model.Team;
import com.example.racing.model.internal.Event;
import com.example.racing.model.internal.Operation;
import com.github.javafaker.Faker;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RabbitListener
public class EventPublisherImpl implements EventPublisher {
    private final String EXCHANGE_TOPIC = Configuration.TOPIC_EXCHANGE_NAME;
    private final String KEY = Configuration.KEY;

    private final RabbitTemplate rabbitTemplate;

    public EventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    @Override
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

        List<Pilot> pilots = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Pilot pilot = new Pilot();
            Faker faker = new Faker();
            pilot.setName(faker.name().firstName());
            pilot.setAge(faker.number().numberBetween(18, 60));
            pilot.setNationality(faker.address().countryCode());
            pilot.setTitles(List.of(faker.name().title(), faker.name().title()));
            pilot.setReport(reports.get(faker.number().numberBetween(0, size - 1)));
            pilots.add(pilot);
        }

        List<Team> teams = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Team team = new Team();
            Faker faker = new Faker();
            team.setBeginner(pilots.get(faker.number().numberBetween(0, size - 1)));
            team.setColor(faker.color().name());
            team.setProfessional(pilots.get(faker.number().numberBetween(0, size - 1)));
            team.setName(faker.name().name());
            team.setBikes(List.of(faker.name().username(), faker.name().username()));
            teams.add(team);
        }


        for(int i = 0; i < size; i++) {
            Event event = new Event(Operation.CREATE);
            event.setOperateOn(reports.get(i));
            rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, KEY, event);
        }

        for(int i = 0; i < size; i++) {
            Event event = new Event(Operation.CREATE);
            event.setOperateOn(pilots.get(i));
            rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, KEY, event);
        }

        for(int i = 0; i < size; i++) {
            Event event = new Event(Operation.CREATE);
            event.setOperateOn(teams.get(i));
            rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, KEY, event);
        }
    }
}
