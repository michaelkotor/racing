package com.example.racing.messages;

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
public class ReceiverEvent {

    private final PilotRepository pilotRepository;
    private final ReportRepository reportRepository;
    private final TeamRepository teamRepository;

    public ReceiverEvent(PilotRepository pilotRepository, ReportRepository reportRepository, TeamRepository teamRepository) {
        this.pilotRepository = pilotRepository;
        this.reportRepository = reportRepository;
        this.teamRepository = teamRepository;
    }

    public void receiveMessage(Event event) throws InterruptedException {
        if(event.getType().equals(Operation.CREATE)) {
            if(event.getOperateOn() instanceof Team) {
                Team team = (Team) event.getOperateOn();
                teamRepository.save(team);
            }
            if(event.getOperateOn() instanceof Pilot) {
                Pilot pilot = (Pilot) event.getOperateOn();
                pilotRepository.save(pilot);
            }
            if(event.getOperateOn() instanceof Report) {
                Report report = (Report) event.getOperateOn();
                reportRepository.save(report);
            }
        }
        System.out.println(event);
        Thread.sleep(1000);
    }
}
