package vip.lj.store.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
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
    UserDetailsService detailsService;

    @Autowired
    public UserServiceImpl(UserMapper mapper, PasswordEncoder encoder, UserDetailsService detailsService) {
        this.mapper = mapper;
        this.encoder = encoder;
        this.detailsService = detailsService;
    }

    @Override
    public void addUser(UserAddDTO dto) {
        if (mapper.getByUsername(dto.getUsername()) != null)
            throw new ServiceException(ServiceException.Detail.usernameNotUnique);
        dto.setPassword(encoder.encode(dto.getPassword()));
        if (dto.getCreatedUser() == null)
            dto.setCreatedUser(dto.getUsername());
        dto.setCreatedTime(LocalDateTime.now());
        mapper.addUser(dto);
    }

    @Override
    public UserLoginVO login(UserAddDTO dto) {
        var info = (UDetails) detailsService.loadUserByUsername(dto.getUsername());
        if (info == null)
            throw new ServiceException(ServiceException.Detail.userNotExists);
        if (!encoder.matches(dto.getPassword(), info.getPassword()))
            throw new ServiceException(ServiceException.Detail.passwordFailed);
        if (!info.isEnabled())
            throw new ServiceException(ServiceException.Detail.disabled);
        var vo = new UserLoginVO();
        vo.setAvatar(info.getAvatar());
        vo.setToken(JwtUtils.getJwtString(info));
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
        var dt = JwtUtils.parseJwt(token);
        if (dto.getUsername() != null && !dto.getUsername().equals(dt.getUsername()))
            throw new ServiceException();
        dto.setId(dt.getId());
        modInfoInternal(dto, dt.getUsername());
    }

    @Override
    public void modPwd(String token, UserPwdDTO dto) {
        var info = JwtUtils.parseJwt(token);
        if (!encoder.matches(dto.getOldPassword(), info.getPassword()))
            throw new ServiceException(ServiceException.Detail.passwordFailed);
        var modDTO = new UserModDTO();
        modDTO.setId(info.getId());
        modDTO.setPassword(encoder.encode(dto.getNewPassword()));
        modInfoInternal(modDTO, info.getUsername());
    }

    void modInfoInternal(UserModDTO dto, String modBy) {
        dto.setModifiedUser(modBy);
        dto.setModifiedTime(LocalDateTime.now());
        mapper.modById(dto);
    }
}
