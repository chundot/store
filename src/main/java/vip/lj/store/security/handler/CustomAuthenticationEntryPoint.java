package vip.lj.store.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.util.ResUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        throw new ServiceException(ServiceException.Detail.notLogged);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(ResUtils.br("用户未登录！"));
    }
}
