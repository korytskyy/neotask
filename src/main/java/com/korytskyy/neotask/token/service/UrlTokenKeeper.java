package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

public interface UrlTokenKeeper {
    Mono<UrlToken> keep(Url url);
    
    Mono<UrlToken> find(Token token);
}
