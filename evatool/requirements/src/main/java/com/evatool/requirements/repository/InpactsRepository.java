
package com.evatool.requirements.repository;

import com.evatool.requirements.entity.Inpacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InpactsRepository extends JpaRepository<Inpacts, UUID> {
}
