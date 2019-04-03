package com.korytskyy.neotask.token.service;

import com.korytskyy.neotask.token.model.Url;
import com.korytskyy.neotask.token.model.UrlToken;

public interface UrlTokenGenerator {
    UrlToken generate(Url url);
}
