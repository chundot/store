package vip.lj.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.mapper.UserMapper;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.vo.UserLoginVO;
import vip.lj.store.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    UserMapper mapper;
    PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserMapper mapper, PasswordEncoder encoder) {
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public void addUser(UserAddDTO dto) {
        if (mapper.getByUsername(dto.getUsername()) != null)
            throw new ServiceException(ServiceException.Detail.usernameNotUnique);
        dto.setPassword(encoder.encode(dto.getPassword()));
        mapper.addUser(dto);
    }

    @Override
    public UserLoginVO login(UserAddDTO dto) {
        var info = mapper.getByUsername(dto.getUsername());
        if (info == null)
            throw new ServiceException(ServiceException.Detail.userNotExists);
        if (!encoder.matches(dto.getPassword(), info.getPassword()))
            throw new ServiceException(ServiceException.Detail.passwordFailed);
        if (info.getIsEnable() == null || info.getIsEnable() == 0)
            throw new ServiceException(ServiceException.Detail.disabled);
        var vo = new UserLoginVO();
        vo.setAvatar(info.getAvatar());
        return vo;
    }
}
