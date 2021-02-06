
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsImpacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InpactsRepository extends JpaRepository<RequirementsImpacts, UUID> {
}
