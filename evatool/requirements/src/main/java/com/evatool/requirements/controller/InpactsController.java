package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Inpacts;
import com.evatool.requirements.repository.InpactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("requirements")
public class InpactsController {

    @Autowired
    private InpactsRepository inpactsRepository;

    @GetMapping("/inpact")
    public List<Inpacts> getInpactsList() {
        return inpactsRepository.findAll();
    }

    @GetMapping("/inpact/{id}")
    public Optional<Inpacts> getInpactsById(@PathVariable UUID id) {
        return inpactsRepository.findById(id);
    }
}
