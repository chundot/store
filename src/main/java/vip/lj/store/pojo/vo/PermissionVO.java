package vip.lj.store.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionVO implements Serializable {
    private Long id;
    private String name;
    private String value;
}
