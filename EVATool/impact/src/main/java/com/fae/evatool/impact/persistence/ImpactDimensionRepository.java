package com.fae.evatool.impact.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//public interface DimensionRepository<Dimension, String> extends JpaRepository {
public interface ImpactDimensionRepository extends CrudRepository<ImpactDimension, String> {

    public Optional<ImpactDimension> findByName(String name);

}
