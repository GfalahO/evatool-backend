package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Stakeholder;
import com.evatool.requirements.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StakeholderController {

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@GetMapping("/stakeholder")
	public List<Stakeholder> getScenarioVariants() {
		return stakeholderRepository.findAll();
	}
}
