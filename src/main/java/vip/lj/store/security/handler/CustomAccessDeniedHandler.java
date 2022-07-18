package vip.lj.store.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.util.ResUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
//        throw new ServiceException(ServiceException.Detail.forbidden);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(ResUtils.br("权限不足，请联系管理员！"));
    }
}
