package com.evatool.requirements.repository;

import com.evatool.requirements.entity.ScenarioVariants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScenarioRepository extends JpaRepository<ScenarioVariants, UUID> {
}
