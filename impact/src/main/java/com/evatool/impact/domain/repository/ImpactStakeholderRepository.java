package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImpactStakeholderRepository extends CrudRepository<ImpactStakeholder, UUID> {

    public Optional<ImpactStakeholder> findByName(String name);

}
