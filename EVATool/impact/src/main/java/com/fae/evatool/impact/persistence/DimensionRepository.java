package com.fae.evatool.impact.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface DimensionRepository<Dimension, String> extends JpaRepository {
public interface DimensionRepository extends CrudRepository<ImpactDimension, String> {

    public ImpactDimension findByName(String name);

}
