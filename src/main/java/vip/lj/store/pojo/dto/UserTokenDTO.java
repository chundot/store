package vip.lj.store.pojo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
public class UserTokenDTO {
    Long id;
    String username;
    String password;
    List<SimpleGrantedAuthority> authorities;
}
