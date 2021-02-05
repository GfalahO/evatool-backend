package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Impact;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactRepository extends CrudRepository<Impact, String> {

}
