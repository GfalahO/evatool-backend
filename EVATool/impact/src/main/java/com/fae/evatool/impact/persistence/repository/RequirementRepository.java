package com.fae.evatool.impact.persistence.repository;

import com.fae.evatool.impact.persistence.entity.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends CrudRepository<Requirement, String> {

}
