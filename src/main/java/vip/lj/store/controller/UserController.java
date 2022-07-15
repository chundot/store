package vip.lj.store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.pojo.dto.UserAddDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.lj.store.pojo.vo.UserLoginVO;
import vip.lj.store.service.UserService;
import vip.lj.store.util.JsonData;
import vip.lj.store.util.Util;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/reg")
    public Object reg(UserAddDTO userAddDTO) {
        //调用userService中的addUser方法
        try {
            service.addUser(userAddDTO);
        } catch (ServiceException e) {
            if (e.getDetail() == ServiceException.Detail.usernameNotUnique)
                return Util.r(400, "用户名已存在");
        }
        //返回JsonResult.ok();
        return Util.r(200, "注册成功");
    }

    @PostMapping("/login")
    public Object login(UserAddDTO userAddDTO) {
        UserLoginVO vo = null;
        try {
            vo = service.login(userAddDTO);
        } catch (ServiceException e) {
            switch (e.getDetail()) {
                case passwordFailed:
                    return Util.br("密码不正确");
                case disabled:
                    return Util.br("用户已被禁用");
                case userNotExists:
                    return Util.br("用户名不存在");
            }
        }
        return Util.ok(vo);
    }
}