package com.example.racing.messages.impl;

import com.example.racing.messages.EventReceiver;
import com.example.racing.model.Pilot;
import com.example.racing.model.Report;
import com.example.racing.model.Team;
import com.example.racing.model.internal.Event;
import com.example.racing.model.internal.Operation;
import com.example.racing.repository.PilotRepository;
import com.example.racing.repository.ReportRepository;
import com.example.racing.repository.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class EventReceiverImpl implements EventReceiver {

    private final PilotRepository pilotRepository;
    private final ReportRepository reportRepository;
    private final TeamRepository teamRepository;

    public EventReceiverImpl(PilotRepository pilotRepository, ReportRepository reportRepository, TeamRepository teamRepository) {
        this.pilotRepository = pilotRepository;
        this.reportRepository = reportRepository;
        this.teamRepository = teamRepository;
    }

    public void receiveMessage(Event event) throws InterruptedException {
        if(event.getType().equals(Operation.CREATE)) {
            if(event.getOperateOn() instanceof Team) {
                Team team = (Team) event.getOperateOn();
                teamRepository.save(team);
                Thread.sleep(500);
            }
            if(event.getOperateOn() instanceof Pilot) {
                Pilot pilot = (Pilot) event.getOperateOn();
                pilotRepository.save(pilot);
            }
            if(event.getOperateOn() instanceof Report) {
                Report report = (Report) event.getOperateOn();
                reportRepository.save(report);
                reportRepository.flush();
            }
        }
        System.out.println(event);
    }
}
