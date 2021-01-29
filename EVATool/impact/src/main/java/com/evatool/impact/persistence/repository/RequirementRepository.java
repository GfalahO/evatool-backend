package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.entity.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends CrudRepository<Requirement, String> {

}
