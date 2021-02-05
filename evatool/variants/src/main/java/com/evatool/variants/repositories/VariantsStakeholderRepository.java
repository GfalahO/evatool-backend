package com.evatool.variants.repositories;

import com.evatool.variants.entities.VariantsStakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantsStakeholderRepository extends CrudRepository<VariantsStakeholder, String> {

}
