package com.example.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootApplication
public class UaaApplication implements CommandLineRunner {

    @Autowired
    private AccountData accountData;

    @Autowired
    private UserAccountData userAccountData;

    @Autowired
    private UserData userData;

    @Autowired
    private ClientData clientData;

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Account account = new Account(1L);
        account = accountData.save(account);

        User user = new User(1L, "João Paulo Merlin", "joao.p.merlin@gmail.com", "admin",
                new BCryptPasswordEncoder().encode("admin"), true, true);
        user = userData.save(user);

        UserAccount userAccount = new UserAccount(1L, user, account);
        userAccountData.save(userAccount);

        Client client = new Client(1L, account, "acme", "acmesecret",
                Set.of("http://localhost:8080/login"), Set.of(GrantType.values()));
        clientData.save(client);
    }
}