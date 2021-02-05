package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Stakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImpactStakeholderRepository extends CrudRepository<Stakeholder, String> {

    public Optional<Stakeholder> findByName(String name);

}
