
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RequirementPointRepository extends JpaRepository<RequirementPoint, UUID> {

    Collection<RequirementPoint> findByRequirementsImpact(RequirementsImpact requirementsImpact);

    Collection<RequirementPoint> findByRequirement(Requirement requirement);
    RequirementPoint findByRequirementAndRequirementsImpact(Requirement requirement, RequirementsImpact requirementsImpact);

}

