package com.example.racing.web;

import com.example.racing.messages.EventPublisher;
import com.example.racing.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class InitController {

    private final EventPublisher eventPublisher;

    public InitController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/init")
    public void initProcess(HttpServletResponse response) throws IOException {
        eventPublisher.init();
        response.sendRedirect("/data");
    }

}