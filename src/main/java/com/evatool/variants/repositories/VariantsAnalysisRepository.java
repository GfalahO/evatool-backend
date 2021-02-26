package com.evatool.variants.repositories;

import com.evatool.variants.entities.VariantAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantsAnalysisRepository extends JpaRepository<VariantAnalysis, UUID> {

}
