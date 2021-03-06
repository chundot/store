package vip.lj.store.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.dto.UserModDTO;
import vip.lj.store.pojo.dto.UserPwdDTO;
import vip.lj.store.pojo.vo.UserAvatarVO;
import vip.lj.store.service.UserService;
import vip.lj.store.util.CfgUtils;
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
        var info = service.login(userAddDTO);
        return ResUtils.ok(info);
    }

    @GetMapping("/info/show")
    @PreAuthorize("hasAuthority('/user/show')")
    public Object getInfo(@RequestHeader(value = "Authentication") String token) {
        return ResUtils.ok(service.getInfo(token));
    }

    @PostMapping("/info/change")
    @PreAuthorize("hasAuthority('/user/update')")
    public Object changeInfo(@RequestHeader(value = "Authentication") String token, UserModDTO dto) {
        service.modInfo(token, dto);
        return ResUtils.ok();
    }

    @PostMapping("/password/change")
    @PreAuthorize("hasAuthority('/user/update')")
    public Object changePwd(@RequestHeader(value = "Authentication") String token, UserPwdDTO dto) {
        service.modPwd(token, dto);
        return ResUtils.ok();
    }

    @PostMapping("/avatar/change")
    @PreAuthorize("hasAuthority('/user/update')")
    public Object changeAvatar(@RequestHeader(value = "Authentication") String token, MultipartFile file) {
        if (!contentTypes.contains(file.getContentType()))
            return ResUtils.br("上传失败，不支持的文件类型");
        var newName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + "_" + file.getOriginalFilename();
        try {
            var newFile = new File(CfgUtils.staticPath + "/images/user");
            if (!newFile.exists() && newFile.mkdirs())
                log.info("文件夹创建成功");
            file.transferTo(new File(CfgUtils.staticPath + "/images/user", newName));
            var dto = new UserModDTO();
            dto.setAvatar(newName);
            service.modInfo(token, dto);
            var vo = new UserAvatarVO();
            vo.setPath(CfgUtils.staticPath + "/images/user/" + newName);
            vo.setNewName(newName);
            return ResUtils.ok(vo);
        } catch (IOException e) {
            log.debug("上传文件失败!");
            return ResUtils.br("上传失败");
        }
    }

    public static List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
}