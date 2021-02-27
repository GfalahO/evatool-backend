package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, UUID> {

    List<Dimension> findAllByType(Dimension.Type type);

}
