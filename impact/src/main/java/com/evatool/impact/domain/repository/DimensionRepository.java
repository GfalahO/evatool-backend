package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, UUID> {

    Optional<Dimension> findByName(String name);

}
