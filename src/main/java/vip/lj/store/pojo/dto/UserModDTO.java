package vip.lj.store.pojo.dto;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class UserModDTO extends BaseEntity {
    private Long id;
    private String username;
    private Long gender;
    private String phone;
    private String email;
    private String avatar;
    private String password;
}
