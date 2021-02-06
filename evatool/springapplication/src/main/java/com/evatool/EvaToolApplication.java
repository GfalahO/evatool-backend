package com.evatool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication(scanBasePackages = "com.evatool")
@EntityScan(basePackages = "com.evatool")
@EnableJpaRepositories(basePackages = "com.evatool")
public class EvaToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaToolApplication.class, args);
    }

}
