package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUrlTokenRepository implements UrlTokenRepository {
    private Map<Token, UrlToken> map = new HashMap<>();
    
    @Override
    public Mono<UrlToken> findByToken(Token token) {
        return Mono.justOrEmpty(map.get(token));
    }
    
    @Override
    public Mono<UrlToken> save(UrlToken urlToken) {
        if (map.containsKey(urlToken.getToken())) {
            return Mono.empty();
        }
        map.put(urlToken.getToken(), urlToken);
        return Mono.just(urlToken);
    }
}
