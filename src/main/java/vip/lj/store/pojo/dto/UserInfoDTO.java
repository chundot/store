package vip.lj.store.pojo.dto;

import lombok.Data;
import vip.lj.store.pojo.vo.PermissionVO;
import java.util.List;

@Data
public class UserInfoDTO {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private Integer isEnable;
    private List<PermissionVO> permissions;
}
