package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class User extends BaseEntity {
    private Long uid;
    private String username;
    private String password;
    private Long gender;
    private String phone;
    private String email;
    private String avatar;
    private Long isEnable;
}
