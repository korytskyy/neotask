package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

public interface UrlTokenRepository {
    Mono<UrlToken> findByToken(Token token);
    
    Mono<UrlToken> save(UrlToken urlToken);
}
