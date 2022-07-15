package vip.lj.store.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.dto.UserModDTO;
import vip.lj.store.pojo.dto.UserPwdDTO;
import vip.lj.store.pojo.vo.UserLoginVO;
import vip.lj.store.service.UserService;
import vip.lj.store.util.JwtUtils;
import vip.lj.store.util.ResUtils;

@Slf4j
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
        service.addUser(userAddDTO);
        //返回JsonResult.ok();
        return ResUtils.r(200, "注册成功");
    }

    @PostMapping("/login")
    public Object login(UserAddDTO userAddDTO) {
        return ResUtils.ok(service.login(userAddDTO));
    }

    @GetMapping("/info/show")
    public Object getInfo(@RequestHeader(value="Authentication") String token) {
        return ResUtils.ok(service.getInfo(token));
    }
    @PostMapping("/info/change")
    public Object changeInfo(@RequestHeader(value="Authentication") String token, UserModDTO dto) {
        service.modInfo(token, dto);
        return ResUtils.ok();
    }
    @PostMapping("/password/change")
    public Object changePwd(@RequestHeader(value="Authentication") String token, UserPwdDTO dto) {
        service.modPwd(token, dto);
        return ResUtils.ok();
    }
}