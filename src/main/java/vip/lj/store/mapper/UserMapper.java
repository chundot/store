package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.dto.UserInfoDTO;
import vip.lj.store.pojo.dto.UserModDTO;
import vip.lj.store.pojo.dto.UserPwdDTO;
import vip.lj.store.pojo.entity.User;

@Repository
public interface UserMapper {
    User getByUsername(String username);

    User getById(Long id);

    void addUser(UserAddDTO userAddDTO);

    void modById(UserModDTO dto);
    void modPwdById(UserPwdDTO dto);
    UserInfoDTO getInfoByUsername(String username);
}
