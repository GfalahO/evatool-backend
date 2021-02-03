package com.evatool.variants.repositories;

import com.evatool.variants.entities.Stakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StakeholderRepository extends CrudRepository<Stakeholder, String> {

}
