package com.korytskyy.neotask.token.controller;

import com.korytskyy.neotask.token.model.Token;
import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.service.UrlTokenKeeper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

public class RequestHandler {
    public static final String TOKEN = "token";
    private static final String URL_PARAM = "url";
    
    private final UrlTokenKeeper urlTokenKeeper;
    
    public RequestHandler(UrlTokenKeeper urlTokenKeeper) {
        this.urlTokenKeeper = urlTokenKeeper;
    }
    
    public Mono<ServerResponse> generateToken(ServerRequest request) {
        Url url = Url.of(request.queryParam(URL_PARAM).orElseThrow(() -> new IllegalArgumentException(URL_PARAM + " parameter is missing")));
        return urlTokenKeeper.keep(url)
                .flatMap(urlToken -> ok().contentType(TEXT_PLAIN).body(fromObject(urlToken.getToken().value())))
                .onErrorResume(e -> status(INTERNAL_SERVER_ERROR).build());
    }
    
    public Mono<ServerResponse> token(ServerRequest request) {
        Token token = Token.of(request.pathVariable(TOKEN));
        return urlTokenKeeper.find(token)
                .flatMap(urlToken -> permanentRedirect(URI.create(urlToken.getUrl().value())).build())
                .onErrorResume(e -> status(INTERNAL_SERVER_ERROR).build())
                .switchIfEmpty(notFound().build());
    }
}
