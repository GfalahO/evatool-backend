package com.evatool.requirements.repository;

import com.evatool.requirements.entity.RequirementsVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequirementsVariantsRepository extends JpaRepository<RequirementsVariant, UUID> {
}
