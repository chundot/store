package vip.lj.store.pojo.dto;

import lombok.Data;

@Data
public class UserModDTO {
    private Long id;
    private final String username;
    private Long gender;
    private String phone;
    private String email;
}
