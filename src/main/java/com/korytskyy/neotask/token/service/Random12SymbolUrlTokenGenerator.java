package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;

import java.security.SecureRandom;
import java.util.Base64;

public class Random12SymbolUrlTokenGenerator implements UrlTokenGenerator {
    private final SecureRandom secureRandom = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getUrlEncoder();
    
    @Override
    public UrlToken generate(Url url) {
        return UrlToken.of(Token.of(generateToken()), url);
    }
    
    private String generateToken() {
        byte[] randomBytes = new byte[8];
        secureRandom.nextBytes(randomBytes);
        
        return encoder.encodeToString(randomBytes);
    }
}
