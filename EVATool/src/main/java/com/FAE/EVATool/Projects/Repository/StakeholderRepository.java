package com.FAE.EVATool.Projects.Repository;

import com.FAE.EVATool.Projects.Model.Stakeholder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface StakeholderRepository extends CrudRepository<Stakeholder, UUID> {

}
