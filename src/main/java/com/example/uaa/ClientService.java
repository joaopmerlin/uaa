package com.example.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    private ClientData clientData;

    @Override
    @Transactional
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientData.findByClientId(clientId).map(e -> {
            e.getRegisteredRedirectUri().size();
            e.getAuthorizedGrantTypes().size();
            return e;
        }).orElseThrow(() -> new ClientRegistrationException("Client not registred"));
    }
}
