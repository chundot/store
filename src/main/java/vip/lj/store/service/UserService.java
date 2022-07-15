package vip.lj.store.service;

import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.vo.UserLoginVO;

public interface UserService {
    void addUser(UserAddDTO dto);
    UserLoginVO login(UserAddDTO dto);
}
