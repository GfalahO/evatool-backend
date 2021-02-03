package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, String> {

}
