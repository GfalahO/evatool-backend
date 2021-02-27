package com.evatool.variants.repositories;

import com.evatool.variants.entities.VariantsAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariantsAnalysisRepository extends JpaRepository<VariantsAnalysis, UUID> {

}
