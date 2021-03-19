package com.example;

import com.example.racing.repository.PilotRepository;
import com.example.racing.repository.ReportRepository;
import com.example.racing.repository.TeamRepository;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(pilotRepository).isNotNull();
        assertThat(reportRepository).isNotNull();
        assertThat(teamRepository).isNotNull();
    }

}
