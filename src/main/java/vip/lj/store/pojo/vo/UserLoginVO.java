package vip.lj.store.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;//jwt数据(包含id,username,authorities)
    private String avatar; //用户头像
}