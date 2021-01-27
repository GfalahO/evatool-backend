package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.entity.Impact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactRepository extends CrudRepository<Impact, String> {

}
