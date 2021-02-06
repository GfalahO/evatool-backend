package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsImpact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RequirementGRRepository extends JpaRepository<RequirementGR, UUID> {

    public Collection<RequirementGR> findByRequirementsImpact(RequirementsImpact impact);

    public Collection<RequirementGR> findByRequirement(Requirement requirement);
}

