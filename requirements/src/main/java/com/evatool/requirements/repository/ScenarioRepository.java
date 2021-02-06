package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsVariants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScenarioRepository extends JpaRepository<RequirementsVariants, UUID> {
}
