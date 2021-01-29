package com.evatool.projects.repository;

import com.evatool.projects.model.Stakeholder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StakeholderRepository extends CrudRepository<Stakeholder, UUID> {

}
