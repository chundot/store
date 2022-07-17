package vip.lj.store.controller.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.util.ResUtils;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(ServiceException.class)
    public Object ServiceExceptionHandler(ServiceException e) {
        switch (e.getDetail()) {
            case passwordFailed:
                return ResUtils.br("密码不正确");
            case disabled:
                return ResUtils.br("用户已被禁用");
            case userNotExists:
                return ResUtils.br("用户名不存在");
            case invalidToken:
                return ResUtils.br("token无效，请重新登录");
            case usernameNotUnique:
                return ResUtils.r(400, "用户名已存在");
            case expiredToken:
                return ResUtils.br("token过期，请重新登录");
            case forbidden:
                return ResUtils.r(403, "权限不足");
            case notLogged:
                return ResUtils.r(403, "未登录，请先登录！");
            default:
                return ResUtils.br("未知错误");
        }
    }
}
