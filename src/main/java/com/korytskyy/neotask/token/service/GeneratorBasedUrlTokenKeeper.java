package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;
import reactor.core.publisher.Mono;

public class GeneratorBasedUrlTokenKeeper implements UrlTokenKeeper {
    private final UrlTokenGenerator urlTokenGenerator;
    private final UrlTokenRepository urlTokenRepository;
    
    public GeneratorBasedUrlTokenKeeper(UrlTokenGenerator urlTokenGenerator, UrlTokenRepository urlTokenRepository) {
        this.urlTokenGenerator = urlTokenGenerator;
        this.urlTokenRepository = urlTokenRepository;
    }
    
    @Override
    public Mono<UrlToken> keep(Url url) {
        UrlToken urlToken = urlTokenGenerator.generate(url);
        return urlTokenRepository.save(urlToken);
    }
    
    @Override
    public Mono<UrlToken> find(Token token) {
        return urlTokenRepository.findByToken(token);
    }
}
