package com.FAE.EVATool.Projects.Repository;

import com.FAE.EVATool.Projects.Model.Analysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, UUID> {

}
