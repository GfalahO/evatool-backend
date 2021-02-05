
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Inpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.Requirement_GR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface Requirement_GRRepository extends JpaRepository<Requirement_GR, UUID> {

    public Collection<Requirement_GR> findByInpacts(Inpacts inpact);

    public Collection<Requirement_GR> findByRequirement(Requirement requirement);
}

