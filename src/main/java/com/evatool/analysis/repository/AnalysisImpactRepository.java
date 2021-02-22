package com.evatool.analysis.repository;

import com.evatool.analysis.model.AnalysisImpacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalysisImpactRepository extends JpaRepository<AnalysisImpacts, UUID> {


}
