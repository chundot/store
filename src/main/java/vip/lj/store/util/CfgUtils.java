package vip.lj.store.util;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.security.Key;

@Component
public class CfgUtils {
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
    public static String staticPath;

    @PostConstruct
    public void init() {
        secret = nsSecret;
        expiration = nsExpiration;
        tokenHeader = nsTokenHeader;
        key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            staticPath = ResourceUtils.getURL("classpath:static").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
