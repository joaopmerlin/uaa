package com.example.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class UaaApplication implements CommandLineRunner {

    @Autowired
    private UserData userData;

    @Autowired
    private ClientData clientData;

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User(1L, "João Paulo Merlin", "joao.p.merlin@gmail.com", "admin", new BCryptPasswordEncoder().encode("admin"), true);
        userData.save(user);

        Client client = new Client(1L, "acme", new BCryptPasswordEncoder().encode("acmesecret"), Set.of("http://localhost:8080/login"), Set.of("authorization_code", "refresh_token", "password", "client_credentials", "implicit"));
        clientData.save(client);
    }
}