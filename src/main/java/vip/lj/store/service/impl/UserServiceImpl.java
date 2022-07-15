package vip.lj.store.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.mapper.UserMapper;
import vip.lj.store.pojo.dto.UserAddDTO;
import vip.lj.store.pojo.dto.UserModDTO;
import vip.lj.store.pojo.dto.UserPwdDTO;
import vip.lj.store.pojo.entity.User;
import vip.lj.store.pojo.vo.UserLoginVO;
import vip.lj.store.security.pojo.UDetails;
import vip.lj.store.service.UserService;
import vip.lj.store.util.JwtUtils;

@Service
@Slf4j
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
        var details = new UDetails();
        details.setId(info.getUid());
        details.setUsername(info.getUsername());
        details.setPassword(info.getPassword());
        vo.setToken(JwtUtils.getJwtString(details));
        return vo;
    }

    @Override
    public User getInfo(String token) {
        log.info("请求头中的Authentication属性值：{}", token);
        var jwt = token.substring(JwtUtils.tokenHeader.length());
        log.info("请求头中jwt数据：{}", jwt);
        var id = JwtUtils.parseJwtGetId(jwt);
        log.info("用户id:{}", id);
        return mapper.getById(id);
    }

    @Override
    public void modInfo(String token, UserModDTO dto) {
        var id = JwtUtils.parseFromBearer(token);
        dto.setId(id);
        mapper.modById(dto);
    }

    @Override
    public void modPwd(String token, UserPwdDTO dto) {
        var info = JwtUtils.parseJwt(token);
        if (!encoder.matches(dto.getOldPassword(), info.getPassword()))
            throw new ServiceException(ServiceException.Detail.passwordFailed);
        dto.setNewPassword(encoder.encode(dto.getNewPassword()));
        dto.setId(info.getId());
        mapper.modPwdById(dto);
    }
}
