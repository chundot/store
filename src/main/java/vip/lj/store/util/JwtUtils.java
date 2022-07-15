package vip.lj.store.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import vip.lj.store.config.TokenConfiguration;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.pojo.dto.UserTokenDTO;
import vip.lj.store.security.pojo.UDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    private static final Long expiration = TokenConfiguration.expiration;
    private static final Key key = TokenConfiguration.key;
    public static String tokenHeader = TokenConfiguration.tokenHeader;

    public static String getJwtString(UDetails details) {
        var claims = new HashMap<String, Object>();
        claims.put("id", details.getId());
        claims.put("username", details.getUsername());
        claims.put("password", details.getPassword());
        claims.put("permissions", JSON.toJSONString(details.getAuthorities()));
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setHeaderParam(Header.CONTENT_TYPE, "HS256")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) //jwt数据是 7天内有效  参数值就是 5小时后的时间
                .signWith(key)
                .compact();
    }

    public static Long parseJwtGetId(String jwt) {
        Long id = null;
        try {
            //获取数据 claims
            var claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            id = Long.parseLong(claims.get("id").toString());
            return id;
        } catch (Exception e) {
            log.error("jwt解析失败！");
            throw new ServiceException(ServiceException.Detail.invalidToken);
        }
    }
    public static UserTokenDTO parseJwt(String jwt) {
        return parseJwt(jwt, true);
    }
    public static UserTokenDTO parseJwt(String jwt, boolean hasBearer) {
        try {
            if (hasBearer)
                jwt = jwt.substring(JwtUtils.tokenHeader.length());
            //获取数据 claims
            var claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            var dto = new UserTokenDTO();
            dto.setId(Long.parseLong(claims.get("id").toString()));
            dto.setUsername(claims.get("username").toString());
            dto.setPassword(claims.get("password").toString());
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("jwt解析失败！");
            throw new ServiceException(ServiceException.Detail.invalidToken);
        }
    }

    public static Long parseFromBearer(String token) {
        return parseJwtGetId(token.substring(JwtUtils.tokenHeader.length()));
    }
}