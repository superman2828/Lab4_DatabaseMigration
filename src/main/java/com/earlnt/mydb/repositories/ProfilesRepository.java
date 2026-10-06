package com.earlnt.mydb.repositories;

import com.earlnt.mydb.entities.Profiles;
import org.springframework.data.repository.CrudRepository;

public interface ProfilesRepository extends CrudRepository<Profiles, Long> {
}