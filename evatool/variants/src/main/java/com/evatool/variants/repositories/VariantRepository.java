package com.evatool.variants.repositories;

import com.evatool.variants.entities.Variant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends CrudRepository<Variant, String> {

}
