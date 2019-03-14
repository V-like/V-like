package com.jmyp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by on 2018/12/29.
 */
@ConfigurationProperties("jwt.config")
public class JwtConfig {

    private String key;
    private int ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
