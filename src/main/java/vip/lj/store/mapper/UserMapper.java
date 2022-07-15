package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.entity.User;

@Repository
public interface UserMapper {
    User getByUsername(String username);
    void addUser(UserAddDTO userAddDTO);
}
