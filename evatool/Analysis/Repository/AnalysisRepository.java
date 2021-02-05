package com.FAE.EVATool.Analysis.Repository;

import com.FAE.EVATool.Analysis.Model.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, String> {

}
