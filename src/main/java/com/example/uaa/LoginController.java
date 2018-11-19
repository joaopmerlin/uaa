package com.example.uaa;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping
    public String login(HttpSession session) {
        SavedRequest savedRequest = SessionSecurityUtil.getSavedRequest(session);
        if (savedRequest == null) {
            return "error";
        }

        String client_id = savedRequest.getParameterValues("client_id")[0];
        return "login";
    }
}
