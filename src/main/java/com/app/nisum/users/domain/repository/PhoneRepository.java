package com.app.nisum.users.domain.repository;

import com.app.nisum.users.domain.model.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Integer> {
}
