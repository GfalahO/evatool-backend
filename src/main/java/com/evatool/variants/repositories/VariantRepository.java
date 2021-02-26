package com.evatool.variants.repositories;

import com.evatool.variants.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<Variant, UUID> {

    Variant findVariantById(UUID id);
}
