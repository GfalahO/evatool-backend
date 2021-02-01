package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Inpacts;
import com.evatool.requirements.repository.InpactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
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
