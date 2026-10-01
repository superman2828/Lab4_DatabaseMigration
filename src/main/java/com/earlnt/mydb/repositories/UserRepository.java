package com.earlnt.mydb.repositories;

import com.earlnt.mydb.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
