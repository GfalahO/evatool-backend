
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsImpacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InpactsRepository extends JpaRepository<RequirementsImpacts, UUID> {
}
