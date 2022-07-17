package vip.lj.store.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.lj.store.ex.ServiceException;
import vip.lj.store.mapper.UserMapper;
import vip.lj.store.security.pojo.UDetails;

import java.util.ArrayList;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    final
    UserMapper mapper;
    public UserDetailsServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dt = mapper.getInfoByUsername(username);
        if (dt == null) throw new ServiceException(ServiceException.Detail.userNotExists);
        var permissions = dt.getPermissions();
        log.info("此用户的权限信息：{}", permissions);
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        for (var permission : permissions) {
            String permissionValue = permission.getValue();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        //下面使用 自定义UserUserDetails来封装数据，这样就不会默认将userDetails保存在session中
        var userDetails = new UDetails();
        userDetails.setId(dt.getId());
        userDetails.setUsername(dt.getUsername());
        userDetails.setPassword(dt.getPassword());
        userDetails.setAvatar(dt.getAvatar());
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}
