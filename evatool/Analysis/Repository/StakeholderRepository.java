package com.FAE.EVATool.Analysis.Repository;

import com.FAE.EVATool.Analysis.Model.Stakeholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StakeholderRepository extends CrudRepository<Stakeholder, String> {

}
