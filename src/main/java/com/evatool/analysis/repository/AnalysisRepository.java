package com.evatool.analysis.repository;

import com.evatool.analysis.model.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, UUID> {

}
