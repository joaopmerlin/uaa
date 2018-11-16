package com.example.uaa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping({"user", "me"})
public class UserController {

    @GetMapping
    public Principal principal(Principal principal) {
        return principal;
    }

}
