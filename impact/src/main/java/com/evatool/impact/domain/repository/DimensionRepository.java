package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, String> {

    Optional<Dimension> findByName(String name);

}
