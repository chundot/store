package vip.lj.store.service;

import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.dto.UserModDTO;
import vip.lj.store.pojo.dto.UserPwdDTO;
import vip.lj.store.pojo.entity.User;
import vip.lj.store.pojo.vo.UserLoginVO;

public interface UserService {
    void addUser(UserAddDTO dto);

    UserLoginVO login(UserAddDTO dto);

    User getInfo(String token);

    void modInfo(String token, UserModDTO dto);

    void modPwd(String token, UserPwdDTO dto);
}
