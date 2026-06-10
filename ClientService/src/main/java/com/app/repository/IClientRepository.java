package com.app.repository;

import com.app.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByCpf(String cpf);
}
