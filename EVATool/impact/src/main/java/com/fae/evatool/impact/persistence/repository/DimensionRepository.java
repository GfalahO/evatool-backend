package com.fae.evatool.impact.persistence.repository;

import com.fae.evatool.impact.persistence.entity.Dimension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, String> {

    public Optional<Dimension> findByName(String name);

}
