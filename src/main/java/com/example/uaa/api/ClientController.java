package com.example.uaa.api;

import com.example.uaa.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientData clientData;

    @PostMapping
    public ClientTO save(@RequestBody @Valid ClientTO clientTO) {
        if (clientTO.getId() != null) {
            valid(clientTO.getId());
        } else {
            if (clientData.existsByClientId(clientTO.getClientId())) {
                throw new RuntimeException("clientId already exists");
            }
        }

        Client client = clientData.save(buildClient(clientTO, getAccount()));
        return buildClientTO(client);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        valid(id);
        clientData.deleteById(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientTO> findOne(@PathVariable Long id) {
        valid(id);
        return clientData.findById(id)
                .map(e -> ResponseEntity.ok(buildClientTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<ClientTO> find(@RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size) {
        Page<Client> clientPage = clientData.findByAccount(getAccount(), PageRequest.of(page, size));

        List<ClientTO> list = clientPage.getContent().stream().map(this::buildClientTO).collect(Collectors.toList());

        return new PageImpl<>(list, clientPage.getPageable(), clientPage.getTotalElements());
    }

    private void valid(Long id) {
        Optional<Client> optionalClient = clientData.findById(id);
        if (optionalClient.isPresent() && !optionalClient.get().getAccount().getId().equals(getAccount().getId())) {
            throw new UserDeniedAuthorizationException("not authorized");
        }
    }

    private ClientTO buildClientTO(Client e) {
        return new ClientTO(
                e.getId(),
                e.getClientId(),
                e.getClientSecret(),
                e.getRegisteredRedirectUri(),
                e.getGrantTypes()
        );
    }

    private Client buildClient(ClientTO e, Account account) {
        return new Client(
                e.getId(),
                account,
                e.getClientId(),
                e.getClientSecret(),
                e.getRedirectUris(),
                e.getGrantTypes()
        );
    }

    private Account getAccount() {
        return clientData.findByClientId(SecurityUtil.getClient()).orElseThrow().getAccount();
    }

}

@Data
@AllArgsConstructor
class ClientTO implements Serializable {

    private Long id;

    @NotEmpty
    private String clientId;

    @NotEmpty
    private String clientSecret;

    private Set<String> redirectUris = new HashSet<>();

    private Set<GrantType> grantTypes = new HashSet<>();

}
