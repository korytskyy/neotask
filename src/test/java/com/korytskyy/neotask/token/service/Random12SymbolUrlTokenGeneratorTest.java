package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class Random12SymbolUrlTokenGeneratorTest {
    private static final int LENGTH = 12;
    
    @Test
    public void shouldGenerateUniqueTokenWithProperLength() {
        //given
        Random12SymbolUrlTokenGenerator generator = new Random12SymbolUrlTokenGenerator();
        Url someUrl = Url.of("http://any.url");
    
        //when
        UrlToken firstToken = generator.generate(someUrl);
        UrlToken secondToken = generator.generate(someUrl);
        
        //then
        assertThat(firstToken.getUrl()).isEqualTo(someUrl);
        assertThat(firstToken.getToken().value()).hasSize(LENGTH);
        assertThat(secondToken.getToken().value()).hasSize(LENGTH);
        assertThat(firstToken).isNotEqualTo(secondToken);
    }
}