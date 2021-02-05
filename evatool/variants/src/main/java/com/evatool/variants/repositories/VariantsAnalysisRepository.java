package com.evatool.variants.repositories;

import com.evatool.variants.entities.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// renamed repository because analysis is owner of the entity and they had already used up the name 'AnalysisRepository'
// spring allows not Beans with same name. Or you can use qualifier annotation see google
@Repository
public interface VariantsAnalysisRepository extends CrudRepository<Analysis, String> {

}
