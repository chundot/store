package vip.lj.store.pojo.entity;
import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class RolePermission extends BaseEntity {
  private long id;
  private long rid;
  private long pid;
}
