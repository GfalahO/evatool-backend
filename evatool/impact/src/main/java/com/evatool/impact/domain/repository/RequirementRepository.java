package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends CrudRepository<Requirement, String> {

}
