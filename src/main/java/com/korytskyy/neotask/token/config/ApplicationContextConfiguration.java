package com.korytskyy.neotask.token.config;

import com.korytskyy.neotask.token.controller.RequestHandler;
import com.korytskyy.neotask.token.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.korytskyy.neotask.token.controller.RequestHandler.TOKEN;
import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ApplicationContextConfiguration {
    @Bean
    public RouterFunction<ServerResponse> routes(RequestHandler requestHandler) {
        return route(GET("/generateToken"), requestHandler::generateToken).and(
                route(GET(format("/token/{%s}", TOKEN)), requestHandler::token));
    }
    
    @Bean
    public RequestHandler requestHandler(UrlTokenKeeper urlTokenKeeper) {
        return new RequestHandler(urlTokenKeeper);
    }
    
    @Bean
    public UrlTokenKeeper urlTokenKeeper(UrlTokenGenerator urlTokenGenerator, UrlTokenRepository urlTokenRepository) {
        return new GeneratorBasedUrlTokenKeeper(urlTokenGenerator, urlTokenRepository);
    }
    
    @Bean
    public UrlTokenGenerator urlTokenGenerator() {
        return new Random12SymbolUrlTokenGenerator();
    }
    
    @Bean UrlTokenRepository urlTokenRepository() {
        return new InMemoryUrlTokenRepository();
    }
}
