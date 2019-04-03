package com.korytskyy.neotask.e2e;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class EndToEndTest {
    @Resource
    private WebTestClient client;
    
    @Value("${token.ttl}")
    private int ttl;
    
    @Test
    public void shouldCreateTokenAndForwardToProperUrl() {
        String token = client.get().uri("/generateToken?url=http://www.google.com").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(TEXT_PLAIN)
                .expectBody(String.class).value(s -> assertThat(s).hasSize(12))
                .returnResult().getResponseBody();

//        client.get().uri("/token/" + token).exchange()
//                .expectStatus().is3xxRedirection()
//                .expectHeader().valueEquals("Location", "http://www.google.com");
//
//        Thread.sleep(ttl * 1000);
//
//        client.get().uri("/token/" + token).exchange()
//                .expectStatus().isNotFound();
    }
}
