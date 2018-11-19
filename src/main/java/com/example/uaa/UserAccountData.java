package com.example.uaa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountData extends JpaRepository<UserAccount, Long> {

    boolean existsByAccountAndUser(Account account, User user);

}
