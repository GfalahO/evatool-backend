package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StakeholderRepository extends JpaRepository<Stakeholder, UUID> {
}
