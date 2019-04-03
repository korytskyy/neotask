package com.korytskyy.neotask.token.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Token {
    private final String value;
    
    private Token(String value) {
        this.value = requireNonNull(value);
    }
    
    public static Token of(String value) {
        return new Token(value);
    }
    
    public String value() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(value, token.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return "Token{'" + value + "'}";
    }
}
