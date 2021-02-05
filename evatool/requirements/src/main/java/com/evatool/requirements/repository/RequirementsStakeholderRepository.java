package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsStakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// renamed because the entity is owned by another module and they have already used up the name 'StakeholderRepository'
// Spring does not allow to have Beans with same name; Or you can use 'qualifier' annotation see google
@Repository
public interface RequirementsStakeholderRepository extends JpaRepository<RequirementsStakeholder, UUID> {
}
