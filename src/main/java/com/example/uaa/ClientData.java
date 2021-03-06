package com.example.uaa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientData extends JpaRepository<Client, Long> {

    Optional<Client> findByClientId(String clientId);

    Page<Client> findByAccount(Account account, Pageable pageable);

    boolean existsByClientId(String clientId);
}
