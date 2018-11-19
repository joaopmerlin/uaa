package com.example.uaa;

import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UaaPrincipalExtractor extends FixedPrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        return super.extractPrincipal(map);
    }
}
