package com.example.racing.web;

import com.example.racing.model.Team;
import com.example.racing.repository.TeamRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {
    private final TeamRepository teamRepository;

    public EventController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/data")
    public String getData(Model model) {
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "data2";
    }

    @ResponseBody
    @GetMapping("/update")
    public List<Team> update() {
        return teamRepository.findAll();
    }
}
