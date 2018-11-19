package com.example.uaa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountData extends JpaRepository<Account, Long> {

}
