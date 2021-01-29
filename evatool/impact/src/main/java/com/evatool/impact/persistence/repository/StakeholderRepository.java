package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.entity.Stakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StakeholderRepository extends CrudRepository<Stakeholder, String> {

    public Optional<Stakeholder> findByName(String name);

}
