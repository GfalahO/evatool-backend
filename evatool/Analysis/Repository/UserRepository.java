package com.FAE.EVATool.Analysis.Repository;

import com.FAE.EVATool.Analysis.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {


}
