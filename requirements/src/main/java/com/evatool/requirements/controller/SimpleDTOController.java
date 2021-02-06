package com.evatool.requirements.controller;

import com.evatool.requirements.dto.SimpleDTO;
import com.evatool.requirements.service.SimpleDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("requirements")
public class SimpleDTOController {

    @Autowired
    SimpleDTOService dtoService;

    @GetMapping("/dto/{id}")
    public List<SimpleDTO> getSimpleDTOs(@PathVariable UUID id) {

        return dtoService.getSimpleDTOs(id);
    }
}
