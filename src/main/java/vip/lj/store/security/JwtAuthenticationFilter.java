package vip.lj.store.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vip.lj.store.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.tokenHeader}")
    private String jwtHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authentication");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(jwtHeader)) {
            log.debug("准备解析jwt数据........");
            var dt = JwtUtils.parseJwt(authHeader);
            log.debug("jwt解析id:{}, username:{}", dt.getId(), dt.getUsername());
            var authentication = new UsernamePasswordAuthenticationToken(dt.getUsername(), null, dt.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("成功将jwt数据存入到Spring Security上下文.....");
            filterChain.doFilter(request, response);
        } else {
            //因为某些请求本身也不要求登录，没有jwt是正常表现
            log.debug("没有获取到jwt数据,直接放行............");
            //继续执行后续的过滤器链
            filterChain.doFilter(request, response);
        }
    }
}
