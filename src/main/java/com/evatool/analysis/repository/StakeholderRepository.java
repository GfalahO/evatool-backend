package com.evatool.analysis.repository;

import com.evatool.analysis.model.Stakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StakeholderRepository extends CrudRepository<Stakeholder, UUID> {

}
