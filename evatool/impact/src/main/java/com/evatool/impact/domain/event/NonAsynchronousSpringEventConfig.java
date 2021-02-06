package com.evatool.impact.domain.event;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Profile("non-async")
public class NonAsynchronousSpringEventConfig {

}
