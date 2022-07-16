package vip.lj.store.pojo.dto;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class UserAddDTO extends BaseEntity {
    private String username;
    private String password;
}
