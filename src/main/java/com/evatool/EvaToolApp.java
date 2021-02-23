package com.evatool;

import com.evatool.requirements.entity.*;
import com.evatool.requirements.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class EvaToolApp {
    public static void main(String[] args) {
        SpringApplication.run(EvaToolApp.class, args);
    }

}
