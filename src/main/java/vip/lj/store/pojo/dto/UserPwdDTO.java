package vip.lj.store.pojo.dto;

import lombok.Data;

@Data
public class UserPwdDTO {
    Long id;
    String oldPassword, newPassword;
}
