package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImpactStakeholderRepository extends CrudRepository<ImpactStakeholder, String> {

    public Optional<ImpactStakeholder> findByName(String name);

}
