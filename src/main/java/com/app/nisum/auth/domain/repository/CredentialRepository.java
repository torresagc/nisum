package com.app.nisum.auth.domain.repository;

import com.app.nisum.auth.domain.model.Credential;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CredentialRepository extends CrudRepository<Credential, UUID> {
    Optional<Credential> findByUserName(String userName);
}
