package com.example.racing.web;

import com.example.racing.messages.EventPublisher;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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