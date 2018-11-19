package com.example.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserData userData;

    @Autowired
    private ClientData clientData;

    @Autowired
    private UserAccountData userAccountData;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userData.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String clientId = "";

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            clientId = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            SavedRequest savedRequest = SecurityUtil.getSavedRequest(request.getSession(false));
            if (savedRequest != null) {
                clientId = savedRequest.getParameterValues("client_id")[0];
            }
        }

        Optional<Client> clientOptional = clientData.findByClientId(clientId);
        if (clientOptional.isPresent() && userAccountData.existsByAccountAndUser(clientOptional.get().getAccount(), user)) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not authorized");
        }
    }
}
