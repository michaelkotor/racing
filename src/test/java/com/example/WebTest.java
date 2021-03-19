package com.example;

import com.example.racing.messages.EventPublisher;
import com.example.racing.repository.PilotRepository;
import com.example.racing.repository.ReportRepository;
import com.example.racing.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * I have to mock all beans used in controllers, because i use redirect,
 * so both controllers are used
 */
@WebMvcTest
public class WebTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventPublisher eventPublisher;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private PilotRepository pilotRepository;

    @MockBean
    private ReportRepository reportRepository;

    @Test
    public void testInit() throws Exception {
        this.mockMvc.perform(get("/update")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));

    }
}
