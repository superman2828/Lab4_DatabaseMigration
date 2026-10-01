package com.earlnt.mydb.repositories;

import com.earlnt.mydb.entities.Addresses;
import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<Addresses, Long> {
}