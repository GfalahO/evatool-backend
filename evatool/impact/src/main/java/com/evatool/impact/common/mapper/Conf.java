package com.evatool.impact.common.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Conf {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
