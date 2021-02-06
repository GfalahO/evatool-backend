package com.evatool.requirements.domain.repository;


import com.evatool.requirements.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@DataJpaTest
public class ScenarioRepositoryTest {

    @Autowired
    private ScenarioRepository scenarioRepository;
}
