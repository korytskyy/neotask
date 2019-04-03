package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

public class DummyUrlTokenKeeper implements UrlTokenKeeper {
    private final UrlTokenGenerator urlTokenGenerator;
    
    public DummyUrlTokenKeeper(UrlTokenGenerator urlTokenGenerator) {
        this.urlTokenGenerator = urlTokenGenerator;
    }
    
    @Override
    public Mono<UrlToken> keepUrl(Url url) {
        return Mono.just(urlTokenGenerator.generate(url));
    }
}
