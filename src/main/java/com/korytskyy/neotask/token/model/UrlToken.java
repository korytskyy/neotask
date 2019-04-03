package com.korytskyy.neotask.token.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UrlToken {
    private final Token token;
    private final Url url;
    
    private UrlToken(Token token, Url url) {
        this.token = requireNonNull(token);
        this.url = requireNonNull(url);
    }
    
    public static UrlToken of(Token token, Url url) {
        return new UrlToken(token, url);
    }
    
    public Token getToken() {
        return token;
    }
    
    public Url getUrl() {
        return url;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlToken urlToken = (UrlToken) o;
        return token.equals(urlToken.token);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
    
    @Override
    public String toString() {
        return "UrlToken{" +
                token +
                ", " + url +
                '}';
    }
}
