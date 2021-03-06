package com.korytskyy.neotask.token.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Url {
    private final String value;
    
    private Url(String value) {
        this.value = requireNonNull(value);
    }
    
    public static Url of(String value) {
        return new Url(value);
    }
    
    public String value() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(value, url.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return "Url{'" + value + "'}";
    }
}
