package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

public class DummyUrlTokenKeeper implements UrlTokenKeeper {
    @Override
    public Mono<UrlToken> keepUrl(Url url) {
        return Mono.just(UrlToken.of(Token.of("123456789012"), url));
    }
}
