package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.entity.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, String> {

}
