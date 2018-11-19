package com.example.uaa;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Client implements ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT", nullable = false)
    private Account account;

    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @ElementCollection
    @JoinTable(
            name = "CLIENT_REDIRECT_URI",
            joinColumns = @JoinColumn(name = "ID", referencedColumnName = "ID")
    )
    @Column(name = "URI", nullable = false)
    private Set<String> registeredRedirectUri;

    @ElementCollection
    @JoinTable(
            name = "CLIENT_GRANT_TYPE",
            joinColumns = @JoinColumn(name = "ID", referencedColumnName = "ID")
    )
    @Column(name = "GRANT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<GrantType> authorizedGrantTypes;

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes.stream().map(e -> e.name().toLowerCase()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return Set.of("openid");
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
