package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, UUID> {

    Optional<Dimension> findByName(String name);

    @Query(value = "select * from imp_dimension where type = ?1", nativeQuery = true)
    List<Dimension> findDimensionsByType(String type);

}
