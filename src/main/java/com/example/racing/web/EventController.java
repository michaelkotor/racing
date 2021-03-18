package com.example.racing.web;

import com.example.racing.model.Report;
import com.example.racing.model.Team;
import com.example.racing.repository.ReportRepository;
import com.example.racing.repository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {
    private final TeamRepository teamRepository;
    private final ReportRepository reportRepository;

    public EventController(TeamRepository teamRepository, ReportRepository reportRepository) {
        this.teamRepository = teamRepository;
        this.reportRepository = reportRepository;
    }

    @GetMapping("/data")
    public String getData(Model model) {
        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);
        return "data2";
    }
}
