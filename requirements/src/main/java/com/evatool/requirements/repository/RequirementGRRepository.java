
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RequirementGRRepository extends JpaRepository<RequirementPoint, UUID> {

    public Collection<RequirementPoint> findByRequirementsImpact(RequirementsImpact requirementsImpact);

    public Collection<RequirementPoint> findByRequirement(Requirement requirement);
    public Collection<RequirementPoint> findByRequirementAndRequirementsImpact(Requirement requirement, RequirementsImpact requirementsImpact);
}

