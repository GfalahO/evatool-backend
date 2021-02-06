
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequirementRepository extends JpaRepository<Requirement, UUID> {
}

