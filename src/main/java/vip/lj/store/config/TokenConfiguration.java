package vip.lj.store.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;

@Component
public class TokenConfiguration {
    @Value("${jwt.secret}")
    private String nsSecret;
    @Value("${jwt.expiration}")
    private Long nsExpiration;
    @Value("${jwt.tokenHeader}")
    private String nsTokenHeader;
    public static String secret;
    public static Long expiration;
    public static Key key;
    public static String tokenHeader;

    @PostConstruct
    public void init() {
        secret = nsSecret;
        expiration = nsExpiration;
        tokenHeader = nsTokenHeader;
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
}
