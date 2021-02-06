package com.evatool.requirements.service;

import com.evatool.requirements.dto.SimpleDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class SimpleDTOService {

    public List<SimpleDTO> getSimpleDTOs(UUID id) {
        return Arrays.asList();
    }
}
