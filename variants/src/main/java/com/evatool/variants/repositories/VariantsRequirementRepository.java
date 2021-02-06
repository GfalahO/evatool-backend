package com.evatool.variants.repositories;

import com.evatool.variants.entities.VariantsRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// renamed because the entity is owned by another module and they have already used up the name 'RequirementRepository'
// Spring does not allow to have Beans with same name; Or you can use 'qualifier' annotation see google
@Repository
public interface VariantsRequirementRepository extends CrudRepository<VariantsRequirement, String> {

}
