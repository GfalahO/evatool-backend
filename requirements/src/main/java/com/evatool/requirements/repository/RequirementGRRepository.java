
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface RequirementGRRepository extends JpaRepository<RequirementGR, UUID> {

    public Collection<RequirementGR> findByRequirementsImpacts(RequirementsImpacts inpact);

    public Collection<RequirementGR> findByRequirement(Requirement requirement);
    public Collection<RequirementGR> findByRequirementAndRequirementsImpacts(Requirement requirement, RequirementsImpacts requirementsImpacts);
}

